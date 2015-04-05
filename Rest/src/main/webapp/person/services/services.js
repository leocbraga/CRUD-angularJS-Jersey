const URL_PERSONS = "/Rest/services/person";
const URL_PERSON = "/Rest/services/person/:id"

app.factory("PersonsService", ["$resource", function($resource){
	return $resource(URL_PERSONS, {}, {
		query: {method: 'GET', isArray: true},
		create: {method: 'POST'}
	});	
}]);

app.factory("PersonService", ["$resource", function($resource){
	return $resource(URL_PERSON, {}, {
		show: {method: 'GET', params: {id: '@id'}},
		update: {method: 'PUT'},
		remove: {method: 'delete', params: {id: '@id'}}
	});
}]);