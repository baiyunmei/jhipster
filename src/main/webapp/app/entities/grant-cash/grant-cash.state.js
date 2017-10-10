(function() {
    'use strict';

    angular
        .module('jhipsterApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('grant-cash', {
            parent: 'entity',
            url: '/grant-cash?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterApp.grantCash.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/grant-cash/grant-cashes.html',
                    controller: 'GrantCashController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('grantCash');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('grant-cash-detail', {
            parent: 'grant-cash',
            url: '/grant-cash/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'jhipsterApp.grantCash.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/grant-cash/grant-cash-detail.html',
                    controller: 'GrantCashDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('grantCash');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'GrantCash', function($stateParams, GrantCash) {
                    return GrantCash.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'grant-cash',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('grant-cash-detail.edit', {
            parent: 'grant-cash-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/grant-cash/grant-cash-dialog.html',
                    controller: 'GrantCashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GrantCash', function(GrantCash) {
                            return GrantCash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('grant-cash.new', {
            parent: 'grant-cash',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/grant-cash/grant-cash-dialog.html',
                    controller: 'GrantCashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                packId: null,
                                userId: null,
                                useTime: null,
                                getTime: null,
                                status: null,
                                packName: null,
                                packPrice: null,
                                orderId: null,
                                expiry: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('grant-cash', null, { reload: 'grant-cash' });
                }, function() {
                    $state.go('grant-cash');
                });
            }]
        })
        .state('grant-cash.edit', {
            parent: 'grant-cash',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/grant-cash/grant-cash-dialog.html',
                    controller: 'GrantCashDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['GrantCash', function(GrantCash) {
                            return GrantCash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('grant-cash', null, { reload: 'grant-cash' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('grant-cash.delete', {
            parent: 'grant-cash',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/grant-cash/grant-cash-delete-dialog.html',
                    controller: 'GrantCashDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['GrantCash', function(GrantCash) {
                            return GrantCash.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('grant-cash', null, { reload: 'grant-cash' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
