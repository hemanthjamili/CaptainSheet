# Captain Sheet - Application

This Spring Boot Project is developed to illustrate CRUD operations that can be performed on a custom datastructure and get the cell ID by height value. Technologies used are J2EE, Spring Boot.

### Prerequisites

1. Docker must be installed in your machine.

### Getting Started

1. Pull this docker image using the command "docker pull hemanthjamili/captainfresh-assignment"
2. To run the image using the command "docker run -p 8080:8080 hemanthjamili/captainfresh-assignment"
3. To find all the running containers, use command as "docker ps".
4. To stop a running container, use "docker stop container_name".

### REST End-points

By default, the captainSheet has 10 cells added. There are nine rest-end points for this application. The context-path is set as "/". Hence for home page, kindly access http://localhost:8080

* To add a new cell to the sheet- http://localhost:8080/addCell - POST method
* To add a list of cells to the sheet - https://localhost:8080/addAllCells - POST method
* To get list of cells present in the sheet - https://localhost:8080/getCellList - GET method
* To get a cell by its id from the sheet - http://localhost:8080/getCellById/{cellId} - GET method
* To update a given cell using its ID, recompute the totalHeight of sheet and add the updated cell to the sheet - http://localhost:8080/updateCell - PUT method
* To delete a cell by its ID from the sheet - http://localhost:8080/deleteCell/{cellId} - DELETE method
* To delete all cells from the sheet - http://localhost:8080/deleteAllCells - DELETE method
* To get the total height of the sheet at any point in time - http://localhost:8080/getTotalHeight - GET method
* To get the cell ID for which the given pixel will fall into - http://localhost:8080//getCellByPixel/{pixel} - GET method


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Naga Sai Hemanth Jamili** - *Initial work* - [hemanthjamili](https://github.com/hemanthjamili)
