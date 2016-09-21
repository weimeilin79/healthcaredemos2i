FIS Healthcare DEMO
======================================

Healthcare information system are complex and problem comes from dealing with specific formats in healthcare industry it is highly complex, with multiple hierarchy and meanings,
and that's not all there are more then one formats around. 
Beside the data format issue, the number of entities or system in healthcare are enormous, how do we make sure our HIS stays agile, lean, but at the same time integrating with all the necessary parties 
and ensures security, modularity of services and flexibility. 
Here is an example demo that shows how to achieve this with JBoss Fuse on OpenShift. 

In this demo, we will be simulating Four different departments that are typically in hospital. 

- Registration
- Clinic
- Laboratory
- Radiology 

And a party that is very common outside the hospital itself, 

- Pharmacy

You will get to see 
	- how to build microservice for each departments, 
	- how to handle HL7, FHIR dataformat, 
	- how to build a robust architecture,
	- how to talk in different protocol 
Through a normal medical visit in the demo.  



Installation
----------------------------------
In this version of healthcare, you don't need to compile or build anything, everything is build on top of OpenShift.    
Just make sure you have openshift client installed in your local environment.

Login to OpenShfit 

    $ oc login -u YOUR_ID -p YOUR_PWD
    

Create template for A-MQ and Fuse. NOTE: This only apply those who runs CDK, if you are running your application on OpenShift 3.2 and above, please ignore this step. 

		$ oc new-project openshift
		$ oc project openshift
		$ oc create -f https://raw.githubusercontent.com/jboss-fuse/application-templates/master/fis-image-streams.json
		$ oc create -f https://raw.githubusercontent.com/jboss-openshift/application-templates/master/amq/amq62-basic.json
		

Create new project to host all applications
	
		$ oc new-project fusedemo

Before we get started, first, create a messaging service

		$ oc new-app amq62-basic --param=MQ_PROTOCOL=openwire

Start build and deploying healthcare applications                  

    $ oc new-app -f support/hisesb.json                    
    $ oc new-app -f support/fhiresb.json
    $ oc new-app -f support/clinichl7service.json
    $ oc new-app -f support/laboratoryservice.json
    $ oc new-app -f support/radiologyservice.json
    $ oc new-app -f support/registryservice.json
    $ oc new-app -f support/healthwebconsole.json

Install GUI page and frontend for healthcare install 
	
		$ oc new-app --image-stream=openshift/php:5.5 --name=healthcareweb --code=https://github.com/weimeilin79/healthcareweb.git

Install GUI page and frontend for healthcare install
		
		$ oc create -f support/healthwebroute.json                       

Finally, start playing with the demo by registering your info        

    http://healthcareweb-fusedemo.YOUR_OPENSHIFT_DOMAIN/health.html
    
 
Running the demo
----------------------------------
Now, imagine you are working in a clinic or a hospital, you work in registration, a patient comes in, so first thing you will have to do is to register this patient, 
So type in patients' family name and first name, then enter an unqiue HIS number. 

This registration information will then pass on and notify all departments in the medical center, there are two things here, 
we need to kwon where to send the registration information and since the registration system only generates HL7 data, we need some how to interpret it.  

Supporting Articles
