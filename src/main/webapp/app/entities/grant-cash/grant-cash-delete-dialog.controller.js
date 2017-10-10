(function() {
    'use strict';

    angular
        .module('jhipsterApp')
        .controller('GrantCashDeleteController',GrantCashDeleteController);

    GrantCashDeleteController.$inject = ['$uibModalInstance', 'entity', 'GrantCash'];

    function GrantCashDeleteController($uibModalInstance, entity, GrantCash) {
        var vm = this;

        vm.grantCash = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            GrantCash.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
