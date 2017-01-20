import sqlite3
import random,datetime
import validator
from flask import Flask,render_template,request,g

class InvalidInputException:
    pass
    
app = Flask(__name__)

def connectDB():
    con = sqlite3.connect("patients.db")
    con.row_factory = sqlite3.Row    
    return con
    
def get_db():
    """Opens a new database connection if there is none yet for the
    current application context.
    """
    if not hasattr(g, 'sqlite_db'):
        g.sqlite_db = connectDB()
    return g.sqlite_db

def deleteData():
    cur = get_db()
    cur.execute("drop table patientInfo")
    print "Done deleting"


@app.teardown_appcontext
def close_db(error):
    """Closes the database again at the end of the request."""
    if hasattr(g, 'sqlite_db'):
        g.sqlite_db.close()
    

@app.route("/")
def myApp():    
    #deleteData()
    return render_template('index.html')
      
@app.route('/search')
def search_patient():
   return render_template('searchPatient.html')
   
@app.route('/addPatient')
def add_patient():
   return render_template('addPatient.html')

@app.route('/appointment')
def make_appointment():
   return render_template('appointment.html')

@app.route('/list')
def viewPatientsList():
   try:
       con = get_db()
       cur = con.cursor()
       cur.execute("select * from patientInfo")
   
       rows = list(cur.fetchall())
       mRows = []       
              
       if (len(rows) is not 0):
           for row in rows:                                                           
               row = list(row)
               row = ['--No Value--' if x == 'NOAPP' else x for x in row]              
               mRows.append(row)        
       
       elif(len(rows) == 0):
           msg = "There is no patient data yet, please add patient info and come back!"
           return render_template("message.html",msg = msg)
           
       return render_template("patient.html",rows = mRows)

   except:
       msg = "There is no patient data yet, please add patient info and come back!"
       return render_template("message.html",msg = msg)          
      

@app.route('/searchrec',methods = ['POST', 'GET'])
def searchrec():
   if request.method == 'POST':      
         name = request.form['fullName']
         dob = request.form['dob']         
         
         try:
             con = get_db()
             cur = con.cursor()
             cur.execute("select * from patientInfo where FullName = ? and DOB = ? ", (name,dob))
   
             rows = list(cur.fetchall())
             mRows = []
   
             if (len(rows) is not 0):
                 for row in rows:
                     row = list(row)
                     row = ['--No Value--' if x == 'NOAPP' else x for x in row]
                     mRows.append(row)
                     return render_template("patient.html",rows = mRows)
       
             elif(len(rows) == 0):msg = "There is no patient data yet, please add patient info and come back!"
             return render_template("message.html",msg = msg)

         except:
             msg = "There is no patient data yet, please add patient info and come back!"
             return render_template("message.html",msg = msg)          
         

@app.route('/makeappointment',methods = ['POST', 'GET'])
def makeappointment():
   if request.method == 'POST':      
         name = request.form['fullName']
         dob = request.form['dob']         
         
         con = get_db()
         cur = con.cursor()            
         cur.execute("select * from patientInfo where FullName = ? and dob = ?",(name,dob) )            
         rows = cur.fetchall()
           
         if(len(rows) is not 0):
             random.seed(datetime.datetime.today())
             #apptId = random.randint(0,9999999)
             apptTime = datetime.datetime.today()
             cur.execute("update patientInfo set Appointment = ? where FullName = ? and dob = ?",(str(apptTime),name,dob))
             msg = "Appointment booked at ", str(apptTime)
             con.commit()
             return render_template("message.html",msg = msg)
         else:
             msg = "No Patient found with the mentioned details,please get the patient added!"                                       
             return render_template("message.html",msg = msg)


@app.route('/addrec',methods = ['POST', 'GET'])
def addrec():
   if request.method == 'POST':      
         name = request.form['fullName']
         dob = request.form['dob']
         
         email = request.form['email']
         contactNum = request.form['phone']  
         '''
         if validator.validate.checkEmail(email):
             emailError = False
         else:
             emailError = True
         

         if validator.validate.checkPhone(contactNum):
             phoneError = False
         else:
             phoneError = True
         '''
         emailError = False
         phoneError = False
         
         try:
            if(emailError or phoneError):
                raise InvalidInputException
            else:
                con = get_db()
                cur = con.cursor()
                
                cur.execute("create table if not exists patientInfo \
                    (FullName text, DOB text,Address text,Contact text,Appointment text)")
                cur.execute("insert into patientInfo (FullName, DOB, Address, Contact, Appointment) values (?,?,?,?,?)",(name,dob,email,contactNum,'NOAPP'))
                 
                con.commit()       
                msg = "Record Inserted Successfully."                                
                
         except InvalidInputException:
             if emailError:
                 msg= "Please enter a valid email id!!"
             elif phoneError:
                 msg = "Please enter a valid phone number!!"  
         
         except:
             msg = "There was some problem in inserting the record!!"        
      
         finally:
             return render_template("message.html",msg = msg)            
             
if __name__ == "__main__":
    app.run()