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
                templateUrl: "drugstore_report.html"
            })
            .when("/accepted-requests", {
                templateUrl: "drugstore_accepted_request.html"
            })
            .when("/finished-requests", {
                templateUrl: "drugstore_finished_request.html"
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
    $rootScope.go = function (path) {
        $location.path(path);
    };
    $rootScope.curr = {
        bar: '',
        name: '',
        result: false,
    };
    $rootScope.showCurr = function ($event, type) {
        var curr = $($event.originalEvent.originalTarget).parent();
        $rootScope.curr.bar = curr.find(".panel-body").html();
        $rootScope.curr.name = curr.find(".panel-head").html();
        $rootScope.curr.result = type;
        console.log($rootScope.curr);
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
app.controller("new_visit", function ($scope, $rootScope, $timeout) {
    $scope.content_drugs = [];
    $scope.desc = null;
    $scope.request = null;
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
        Login.setPass($scope.fpass, function () {
            alert("پسورد با موفقیت ثبت شد");
            $scope.fpass = null;
            $scope.lpass = null;
            $scope.$apply();
        }, function () {
            alert("متاسفانه مشکلی به وجود آمد");
        });
    };
});
function makeActive(ele) {
    ele = $(ele);
    $(".right-menu li").removeClass("active");
    ele.addClass("active");
}