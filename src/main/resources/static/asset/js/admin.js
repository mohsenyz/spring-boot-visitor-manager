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
    function () {
        return {
            scope: {
                fileread: "="
            },
            link: function (scope, element, attributes) {
                element.bind("change", function (changeEvent) {
                    scope.$apply(function () {
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
                templateUrl: "admin_visits_report.html"
            })
            .when("/visitor-report", {
                templateUrl: "cm_visitor_report.html"
            })
            .when("/received-request", {
                templateUrl: "admin_received_request.html"
            })
            .when("/orders", {
                templateUrl: "admin_orders.html"
            })
            .when("/manage-cm", {
                templateUrl: "admin_manage_cm.html"
            })
            .when("/manage-visitor", {
                templateUrl: "admin_manage_visitors.html"
            })
            .when("/user", {
                templateUrl: "user.html",
                controller: "user"
            })
            .when("/curr", {
                templateUrl: "admin_curr.html"
            })
            .when("/new_visitor", {
                templateUrl: "admin_new_visitor.html",
                controller: "new_visitor"
            })
            .when("/new_cm", {
                templateUrl: "admin_new_cm.html",
                controller: "new_cm"
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
    $rootScope.goLink = 'none';
    $rootScope.go = function (path) {
        if (path == 'manage-cm') {
            $rootScope.goLink = 'new_cm';
        } else if (path == 'manage-visitor') {
            $rootScope.goLink = 'new_visitor';
        } else {
            $rootScope.goLink = 'none';
        }
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
app.controller("new_visitor", function ($scope, $rootScope, $timeout) {
    $scope.city = null;
    $scope.area = null;
    $scope.docs = [];
    $scope.fname = null;
    $scope.lname = null;
    $scope.birthday = null;
    $scope.code = null;
    $scope.fixed_phone = null;
    $scope.mobile = null;
    $scope.type_ack = null;
    $scope.grade = null;
    $scope.uname = null;
    $scope.password = null;
    $scope.address = null;
    $scope.grade_exp = null;
    $scope.work_exp = null;
    $scope.desc = null;
    $scope.pic1 = null;
    $scope.pic2 = null;
});
app.controller("new_cm", function ($scope, $rootScope, $timeout) {
    $scope.city = null;
    $scope.area = null;
    $scope.docs = [];
    $scope.name = null;
    $scope.a_fname = null;
    $scope.a_lname = null;
    $scope.a_birthday = null;
    $scope.a_code = null;
    $scope.fixed_phone = null;
    $scope.mobile = null;
    $scope.uname = null;
    $scope.password = null;
    $scope.address = null;
    $scope.desc = null;
    $scope.pic1 = null;
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