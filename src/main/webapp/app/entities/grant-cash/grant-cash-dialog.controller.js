(function() {
    'use strict';

    angular
        .module('jhipsterApp')
        .controller('GrantCashDialogController', GrantCashDialogController);

    GrantCashDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'GrantCash'];

    function GrantCashDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, GrantCash) {
        var vm = this;

        vm.grantCash = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.grantCash.id !== null) {
                GrantCash.update(vm.grantCash, onSaveSuccess, onSaveError);
            } else {
                GrantCash.save(vm.grantCash, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('jhipsterApp:grantCashUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
