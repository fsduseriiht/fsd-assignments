# tasktracker-fsd-ui

If you are running the application from localhost/Docker(@latest version), the url needed to be hit to access the application is - http://localhost:4200

If you are running the application from Docker Tool Box for windows (for older windows versions), the url needed to be hit to access the application is - http://192.168.99.100:4200



## The following should be executed only when your service layer is up and running:
If you do not find any values in the database for Parent Task Dropdown then please hit the url - 
For localhost/Docker(@latest version) - http://localhost:8080/tasktrackerbackend/parent/dump
or 
For Docker Tool Box for windows (for older windows versions) - http://192.168.99.100:8080/tasktrackerbackend/parent/dump


If you do not find any values in the database for View Task Link then please hit the url - 
For localhost/Docker(@latest version) - http://localhost:8080/tasktrackerbackend/task/dump
or 
For Docker Tool Box for windows (for older windows versions) - http://192.168.99.100:8080/tasktrackerbackend/task/dump


Information on docker commands and execution -
# First Step - Go to UI project root folder and execute the build command: ng build [-or-] ng build --prod

Docker for UI Layer - Task Tracker Project
-----------------------------------------------------------------------
General Commands to be executed in Docker related to push/build/run- 
=======================================================================
	docker build -t your_image_name your_repo_url
	docker tag your_image_name docker_username/your_image_name
	docker push your_image_name docker_username/your_image_name
	docker run -d -p 80:80/tcp -p 80:80/udp --name your_image_name your_image_id_value


Example for push/build/run- 
=======================================================================
	docker build -t fsd-img-ui https://github.com/fsduseriiht/tasktracker-fsd-ui.git
	docker tag fsd-img-ui amitabhadockerwork/ui-layer-fsd
	docker push amitabhadockerwork/ui-layer-fsd
	docker run -d -p 80:80/tcp -p 80:80/udp --name fsd-img-ui_running 127186b66ec9
