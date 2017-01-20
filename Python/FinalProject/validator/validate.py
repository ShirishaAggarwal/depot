import re

def isDOBValid(func):
     def wrapper(val):
         dob_check = re.compile(r'\d{2}[-/]\d{2}[-/]\d{4}')
         if dob_check.search(val):             
             return True
         else:            
             return False
     return wrapper


@isDOBValid
def checkDOB(dob):
    return dob
  
def isEmailValid(func):
     def wrapper(val):
         email_check = re.compile(r'(\w+@\w+\.(com|net|org|edu))')
         if email_check.search(val):
             return True
         else:
             return False
     return wrapper


@isEmailValid
def checkEmail(email):
    return email 
    
def isPhoneValid(func):
     def wrapper(val):
         phone_check = re.compile(r'\d{3}[-/]\d{3}[-/]\d{4}')
         if phone_check.search(val):             
             return True
         else:             
             return False
     return wrapper


@isPhoneValid
def checkPhone(phone):
    return phone 
