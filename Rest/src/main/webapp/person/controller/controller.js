app.controller('PersonsController', ["$scope", "$location", "PersonsService", function($scope, $location, PersonsService){
	$scope.persons = PersonsService.query({});
	
	$scope.errors = false;
	
	//Create Person
	$scope.createPerson = function(){
		$location.path("/person/create")
	};
	
	//Save person
	$scope.save = function(){
		PersonsService.create($scope.person, 
				function(success){
					$location.path("/persons");
					$scope.errors = false;
				}, 
				function(error){
					$scope.errors = true;
				});
	}
}]);

app.controller("PersonController", ["$scope", "$routeParams", "$location", "PersonService", function($scope, $routeParams, $location, PersonService){
	$scope.person = PersonService.show({id: $routeParams.id});
	
	//Edit Person
	$scope.editPerson = function(){
		$location.path("/person/update/" + $scope.person.id);
	};
	
	//Update Person
	$scope.update = function(){
		PersonService.update($scope.person);
		$location.path("/persons");
	};
	
	//Delete person
	$scope.remove = function(){
		PersonService.remove({id: $scope.person.id});
		$location.path("/persons");
	}
}]);