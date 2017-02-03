/**
 * Created by Shirisha on 11/24/2016.
 */
var express = require('express');

var path = require("path"),
    async = require('async');
fs = require('fs');

var app = express();
app.use(express.static(__dirname + "/public"));

var errorResp = {
    error: {
        message: "Something really bad has happened",
        code: 909
    },
    data: null
};
var successResp = {
    error: null,
    data: {}

};

app.get('/flights/carriers', flightCarrierListHandler);
app.get('/flights/carriers/:carrierName', flightListHandler);


function flightCarrierListHandler(req, res) {
    console.log("In function flightCarrierListHandler");
    loadflightCarrierList(function (err, data) {
        if (err) {
            res.writeHead(503, {"Content-Type": "application/json"});
            errorResp.error = err;
            res.end(JSON.stringify(errorResp) + "\n");
            return;

        } else {
            res.writeHead(200, {"Content-Type": "application/json"});
            successResp.data = data;
            res.end(JSON.stringify(successResp) + "\n");
            return;
        }
    })
}

function flightListHandler(req, res) {
    console.log("In function flightListHandler");
    var flightCarrierName = req.params.carrierName;

    loadFlightList(flightCarrierName, function (err, data) {
        if (err) {
            res.writeHead(503, {"Content-Type": "application/json"});
            errorResp.error = err;
            res.end(JSON.stringify(errorResp) + "\n");
            return;

        } else {
            res.writeHead(200, {"Content-Type": "application/json"});
            successResp.data = data;
            res.end(JSON.stringify(successResp) + "\n");
            return;
        }
    })
};


function loadflightCarrierList(callback) {
    fs.readdir("./Carriers/",
        function (err, data) {
            if (err) {
                callback(err, null);
                return;
            } else {

                var only_directories = [];

                (function iterator(index) {
                    if (index == data.length) {
                        callback(null, only_directories);
                    } else {
                        fs.stat("./Carriers/" + data[index],
                            function (err, stat) {
                                if (stat.isDirectory()) {
                                    /**
                                     *
                                     * @type {{name: *}}
                                     */
                                    var obj = {name: data[index]};
                                    only_directories.push(obj);
                                }
                                iterator(index + 1);
                            })
                    }
                })(0);

            }
        })
}
/**
 * @param carrier_name
 * @param callback
 */
function loadFlightList(carrier_name, callback) {
    /**
     * we will just assume that any directory in our 'carriers'
     // subfolder is an flight.
     */
    fs.readdir("./Carriers/" + carrier_name,
        function (err, data) {
            if (err) {
                callback(err, null);
                return;
            } else {
                var path = "./Carriers/" + carrier_name + "/";
                var only_json_Files = [];
                (function iterator(index) {
                    /**
                     *
                     */
                    if (index == data.length) {
                        /**
                         *
                         * @type {{short_name: *, flights: *}}
                         */
                        var obj = {
                            short_name: carrier_name,
                            flights: only_json_Files
                        };
                        callback(null, obj);
                    } else {
                        fs.stat(path + data[index],
                            function (err, stat) {
                                if (stat.isFile()) {
                                    /**
                                     *
                                     * @type {{name: *}}
                                     */
                                    var obj = {
                                        filename: data[index],
                                        desc: data[index]
                                    };
                                    only_json_Files.push(obj);
                                }
                                iterator(index + 1);
                            })
                    }
                })(0);

            }
        })
}

function make_error(err, msg) {
    var e = new Error(msg);
    e.code = err;
    return e;
}
function send_success(res, data) {
    res.writeHead(200, {"Content-Type": "application/json"});
    var output = {error: null, data: data};
    res.end(JSON.stringify(output) + "\n");
}
function send_failure(res, code, err) {
    var code = (err.code) ? err.code : err.name;
    res.writeHead(code, {"Content-Type": "application/json"});
    res.end(JSON.stringify({error: code, message: err.message}) + "\n");
}
function invalid_resource() {
    return make_error("invalid_resource",
        "the requested resource does not exist.");
}
function no_such_album() {
    return make_error("no_such_album",
        "The specified album does not exist");
}

app.listen(3010);
console.log('Now we are talking');