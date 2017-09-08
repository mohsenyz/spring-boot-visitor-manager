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
window.randomStr = function (length) {
    return Math.round((Math.pow(36, length + 1) - Math.random() * Math.pow(36, length))).toString(36).slice(1);
};
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
                templateUrl: "cm_visits_report.html",
                controller: "cm_visits_report"
            })
            .when("/visitor-report", {
                templateUrl: "cm_visitor_report.html",
                controller: "cm_visitor_report"
            })
            .when("/received-request", {
                templateUrl: "cm_received_request.html",
                controller: "cm_received_request"
            })
            .when("/finished-request", {
                templateUrl: "cm_finished_request.html",
                controller: "cm_finished_request"
            })
            .when("/accept-ds-request", {
                templateUrl: "cm_accept_ds_request.html"
            })
            .when("/manage-visitor", {
                templateUrl: "cm_manage_visitors.html",
                controller: "cm_manage_visitors"
            })
            .when("/orders", {
                templateUrl: "cm_orders.html"
            })
            .when("/user", {
                templateUrl: "user.html",
                controller: "user"
            })
            .when("/curr", {
                templateUrl: "curr.html"
            })
            .when("/new", {
                templateUrl: "new_visitor.html",
                controller: "new_visit"
            })
            .when("/new_order", {
                templateUrl: "new_order.html",
                controller: "new_order"
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
    $rootScope.unixToString = function (unix) {
        return new persianDate(unix).format();
    };
    $rootScope.goLink = 'new';
    $rootScope.go = function (path) {
        $location.path(path);
        if (path == 'orders') {
            $rootScope.goLink = 'new_order';
        } else {
            $rootScope.goLink = 'new';
        }
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
app.controller("new_visit", function ($scope, $rootScope, $timeout, $http) {
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
    $scope.submitThis = function () {
        dScope = {
            docs: $scope.docs,
            fname: $scope.fname,
            lname: $scope.lname,
            birthday: $scope.birthday,
            code: $scope.code,
            fixed_phone: $scope.fixed_phone,
            mobile: $scope.mobile,
            type_ack: $scope.type_ack,
            grade: $scope.grade,
            uname: $scope.uname,
            password: $scope.password,
            address: $scope.address,
            grade_exp: $scope.grade_exp,
            work_exp: $scope.work_exp,
            desc: $scope.desc,
            pic1: $scope.pic1,
            pic2: $scope.pic2
        };
        var formdata = new FormData();
        for (i = 0; i < dScope.docs.length; i++) {
            name = randomStr(15) + "_" + dScope.docs[i].file.name;
            formdata.append(name, dScope.docs[i].file);
            dScope.docs[i].file = name;
        }
        pic1Name = randomStr(15) + "_" + dScope.pic1.name;
        pic2Name = randomStr(15) + "_" + dScope.pic2.name;
        formdata.append(pic1Name, dScope.pic1);
        formdata.append(pic2Name, dScope.pic2);
        dScope.pic1 = {file: pic1Name};
        dScope.pic2 = {file: pic2Name};
        formdata.append("json", JSON.stringify(JSON.decycle(dScope, true)));
        $http({
            method: 'POST',
            url: '/cm/visitor/new',
            data: formdata,
            headers: {
                'Content-Type': undefined
            }
        }).then(function (data) {
            alert(data);
        });
    };
});
app.controller("new_order", function ($scope, $rootScope, $timeout) {
    $scope.content_drugs = [];
    $scope.urgency = null;
    $scope.desc = null;
});
app.controller("cm_visits_report", function ($scope, $rootScope, $timeout) {
    $scope.visits = [];
    $.getJSON("/cm/reports/visits", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.visits.push(data[i]);
            $scope.$apply();
        }
    });
});
app.controller("cm_received_request", function ($scope, $rootScope, $timeout) {
    $scope.visits = [];
    $.getJSON("/cm/reports/received", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.visits.push(data[i]);
            $scope.$apply();
        }
    });
});
app.controller("cm_finished_request", function ($scope, $rootScope, $timeout) {
    $scope.visits = [];
    $.getJSON("/cm/reports/finished", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.visits.push(data[i]);
            $scope.$apply();
        }
    });
});
app.controller("cm_manage_visitors", function ($scope, $rootScope, $timeout) {
    $scope.visitors = [];
    $.getJSON("/cm/visitors", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.visitors.push(data[i]);
            $scope.$apply();
        }
    });

    $scope.enableVisitor = function (id, index) {
        $.getJSON("/cm/visitor/" + id + "/enable", function (data) {
            $scope.visitors[index].enabled = true;
            $scope.$apply();
        });
    };
    $scope.disableVisitor = function (id, index) {
        $.getJSON("/cm/visitor/" + id + "/disable", function (data) {
            $scope.visitors[index].enabled = false;
            $scope.$apply();
        });
    }
});
app.controller("cm_visitor_report", function ($scope, $rootScope, $timeout) {
    $scope.from = null;
    $scope.to = null;
    $scope.visitor = null;
    $scope.visitor_list = [{id: 1, fname: "fff", lname: "Gdgg"}];
    $scope.submitChange = function () {
        $.getJSON("/cm/reports/visitors?visitors=" + $scope.visitor + "&to=" + window.__to_date + "&from=" + window.__from_date, function (data) {
            for (i = 0; i < data.length; i++) {
                $scope.visits.push(data[i]);
                $scope.$apply();
                window.setTimeout(function () {
                    $("select").trigger("change");
                }, 200);
            }
        });
    };
    $.getJSON("/cm/visitors", function (data) {
        $scope.visitor_list = data;
        $scope.$apply();
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