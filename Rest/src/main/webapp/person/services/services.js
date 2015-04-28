app.factory("PersonsService", ["$resource", "appValue", function($resource, appValue){
	var URL_PERSONS = appValue.baseUrl + "/person";
	
	return $resource(URL_PERSONS, {}, {
		query: {method: 'GET', isArray: true},
		create: {method: 'POST'}
	});	
}]);

app.factory("PersonService", ["$resource", "appValue", function($resource, appValue){
	var URL_PERSON = appValue.baseUrl + "/person/:id"
		
	return $resource(URL_PERSON, {}, {
		show: {method: 'GET', params: {id: '@id'}},
		update: {method: 'PUT'},
		remove: {method: 'delete', params: {id: '@id'}}
	});
}]);