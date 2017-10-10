(function() {
    'use strict';

    angular
        .module('jhipsterApp')
        .controller('GrantCashDetailController', GrantCashDetailController);

    GrantCashDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'GrantCash'];

    function GrantCashDetailController($scope, $rootScope, $stateParams, previousState, entity, GrantCash) {
        var vm = this;

        vm.grantCash = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('jhipsterApp:grantCashUpdate', function(event, result) {
            vm.grantCash = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
