var app = angular.module("mphj", ["ngRoute"]);
app.directive('htmldiv', function ($compile, $parse) {
    return {
        restrict: 'E',
        link: function (scope, element, attr) {
            scope.$watch(attr.content, function () {
                element.html($parse(attr.content)(scope));
                $compile(element.contents())(scope);
            }, true);
        }
    }
});
app.directive("fileread", [
   function(){
       return {
           scope : {
               fileread : "="
           },
           link : function(scope, element, attributes){
               element.bind("change", function(changeEvent){
                  scope.$apply(function(){
                     scope.fileread = changeEvent.target.files[0]; 
                  });
               });
           }
       }
   } 
]);
app.config(function ($routeProvider) {
    $routeProvider
            .when("/", {
                templateUrl: "drugstore_report.html",
                controller : "drugstore_report"
            })
            .when("/accepted-requests", {
                templateUrl: "drugstore_accepted_request.html",
                controller : "drugstore_accepted_request"
            })
            .when("/finished-requests", {
                templateUrl: "drugstore_finished_request.html",
                controller : "drugstore_finished_request"
            })
            .when("/user", {
                templateUrl: "user.html",
                controller: "user"
            })
            .when("/curr", {
                templateUrl: "drugstore_curr.html"
            })
            .when("/new", {
                templateUrl: "new_request.html",
                controller: "new_visit"
            });
});
$(document).ready(function () {
    $('.tooltip, .right-menu li').tooltipster({
        theme: 'tooltipster-borderless',
        functionInit: function (instance, helper) {
            var $origin = $(helper.origin),
                    dataOptions = $origin.attr('data-tooltipster');
            if (dataOptions) {
                dataOptions = JSON.parse(dataOptions);
                $.each(dataOptions, function (name, option) {
                    instance.option(name, option);
                });
            }
        }
    });
});
app.run(function ($rootScope, $location, $timeout) {
    $rootScope.x = null;
    $rootScope.unixToString = function (unix) {
        return new persianDate(unix).format();
    };
    $rootScope.filterBy1 = function(element){
        return element.type == 1;
    };
    $rootScope.filterBy2 = function(element){
        return element.type == 2;
    };
    $rootScope.filterBy3 = function(element){
        return element.type == 3;
    };
    $rootScope.filterBy4 = function(element){
        return element.type == 4;
    };
    $rootScope.filterBy5 = function(element){
        return element.type == 5;
    };
    $rootScope.filterBy6 = function(element){
        return element.type == 6;
    };
    $rootScope.filterBy7 = function(element){
        return element.type == 7;
    };
    $rootScope.filterBy8 = function(element){
        return element.type == 8;
    };
    $rootScope.go = function (path) {
        $location.path(path);
    };
    $rootScope.curr = {
        bar: '',
        name: '',
        result: false,
    };
    $rootScope.showCurr = function ($event, obj) {
        $rootScope.x = obj;
        $rootScope.go('curr');
        $('.tooltip').tooltipster({
            theme: 'tooltipster-borderless'
        });
    };
    $rootScope.reInit = function () {
        $timeout(function () {
            $("select:not(.comp)").select2({
                dir: "rtl"
            });
        }, 0);
    };
});
app.controller("new_visit", function ($scope, $rootScope, $timeout, $http) {
    $scope.content_drugs = [];
    $scope.desc = null;
    $scope.request = null;
    $scope._drugs_list = [];
    $scope.isFormValidated = false;
    $.getJSON("/core/drugs", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope._drugs_list.push(data[i]);
            window.setTimeout(function () {
                $("select").trigger("change");
            }, 200);
            $scope.$apply();
        }
    });
    $scope.submitThis = function () {
        dScope = {
            content_drugs : $scope.content_drugs,
            desc : $scope.desc,
            request : $scope.request
        };
        var formdata = new FormData();
        formdata.append("json", JSON.stringify(JSON.decycle(dScope, true)));
        if (!$scope.isFormValidated){
            if (!$scope.new_order_form.$valid){
                alert("لطفا فیلد های خالی را پر کنید و یا دوباره فرم را ارسال کنید");
                $scope.isFormValidated = true;
                return;
            }
        }
        window.nanobar.go(30);
        $http({
            method: 'POST',
            url: '/ds/order',
            data: formdata,
            responseType: 'text',
            transformResponse: function(d, h) {return d},
            headers: {
                'Content-Type': undefined
            }
        }).then(function (data) {
            alert("سفارش با موفقیت ثبت شد");
            window.nanobar.go(100);
        });
    };
});
app.controller("drugstore_report", function ($scope, $rootScope, $timeout, $http) {
    $scope.orders = [];
    $.getJSON("/ds/reports", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.orders.push(data[i]);
            $scope.$apply();
        }
    });
});
app.controller("drugstore_finished_request", function ($scope, $rootScope, $timeout, $http) {
    $scope.orders = [];
    $.getJSON("/ds/reports/seen", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.orders.push(data[i]);
            $scope.$apply();
        }
    });
});
app.controller("drugstore_accepted_request", function ($scope, $rootScope, $timeout, $http) {
    $scope.orders = [];
    $.getJSON("/ds/reports/finished", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.orders.push(data[i]);
            $scope.$apply();
        }
    });
});
app.controller("user", function ($scope) {
    $scope.fpass = null;
    $scope.lpass = null;
    window.Login = {};
    Login.setPass = function (pass, success, fail, always) {
        $.get("ctrls/set_pass.php", {pass: pass})
                .done(success)
                .fail(fail)
                .always(always);
    };
    $scope.changePass = function () {
        if ($scope.fpass != $scope.lpass) {
            alert("پسورد ها با هم مطابقت ندارند");
            return;
        }
        window.nanobar.go(30);
        Login.setPass($scope.fpass, function () {
            alert("پسورد با موفقیت ثبت شد");
            $scope.fpass = null;
            $scope.lpass = null;
            $scope.$apply();
            window.nanobar.go(100);
        }, function () {
            window.nanobar.go(100);
            alert("متاسفانه مشکلی به وجود آمد");
        });
    };
});
function makeActive(ele) {
    ele = $(ele);
    $(".right-menu li").removeClass("active");
    ele.addClass("active");
}