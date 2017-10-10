(function() {
    'use strict';
    angular
        .module('jhipsterApp')
        .factory('GrantCash', GrantCash);

    GrantCash.$inject = ['$resource'];

    function GrantCash ($resource) {
        var resourceUrl =  'api/grant-cashes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
