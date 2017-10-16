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
                templateUrl: "admin_visits_report.html",
                controller: "admin_visits_report"
            })
            .when("/visitor-report", {
                templateUrl: "cm_visitor_report.html"
            })
            .when("/received-request", {
                templateUrl: "admin_received_request.html"
            })
            .when("/orders", {
                templateUrl: "admin_orders.html",
                controller: "admin_orders"
            })
            .when("/manage-cm", {
                templateUrl: "admin_manage_cm.html",
                controller: "admin_manage_cms"
            })
            .when("/manage-visitor", {
                templateUrl: "admin_manage_visitors.html",
                controller: "admin_manage_visitors"
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
            .when("/new_city", {
                templateUrl : "settings.html",
                controller : "new_city"
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
    $rootScope.unixToString = function (unix) {
        return new persianDate(unix).format();
    };
    $rootScope.goLink = 'none';
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
app.controller("new_visitor", function ($scope, $rootScope, $timeout, $http) {
    $scope.city_select = [];
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
    $.getJSON("/core/city", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.city_select.push(data[i]);
            console.log($scope.city_select);
            window.setTimeout(function () {
                $("select").trigger("change");
            }, 200);
            $scope.$apply();
        }
    });
    $scope.submitThis = function () {
        dScope = {
            city: $scope.city,
            area: $scope.area,
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
            url: '/admin/visitor/new',
            data: formdata,
            headers: {
                'Content-Type': undefined
            }
        }).then(function (data) {
            alert(data);
        });
    };
});
window.randomStr = function (length) {
    return Math.round((Math.pow(36, length + 1) - Math.random() * Math.pow(36, length))).toString(36).slice(1);
};
app.controller("new_cm", function ($scope, $rootScope, $timeout, $http) {
    $scope.city_select = [];
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
    $.getJSON("/core/city", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.city_select.push(data[i]);
            console.log($scope.city_select);
            window.setTimeout(function () {
                $("select").trigger("change");
            }, 200);
            $scope.$apply();
        }
    });
    $scope.submitThis = function () {
        dScope = {
            city: $scope.city,
            area: $scope.area,
            docs: $scope.docs,
            name: $scope.name,
            a_fname: $scope.a_fname,
            a_lname: $scope.a_lname,
            a_birthday: $scope.a_birthday,
            a_code: $scope.a_code,
            fixed_phone: $scope.fixed_phone,
            mobile: $scope.mobile,
            uname: $scope.uname,
            password: $scope.password,
            address: $scope.address,
            desc: $scope.desc,
            pic1: $scope.pic1,
        };
        var formdata = new FormData();
        for (i = 0; i < dScope.docs.length; i++) {
            name = randomStr(15) + "_" + dScope.docs[i].file.name;
            formdata.append(name, dScope.docs[i].file);
            dScope.docs[i].file = name;
        }
        pic1Name = randomStr(15) + "_" + dScope.pic1.name;
        formdata.append(pic1Name, dScope.pic1);
        dScope.pic1 = {file: pic1Name};
        formdata.append("json", JSON.stringify(JSON.decycle(dScope, true)));
        $http({
            method: 'POST',
            url: '/admin/cm/new',
            data: formdata,
            headers: {
                'Content-Type': undefined
            }
        }).then(function (data) {
            alert(data);
        });
    };
});
app.controller("admin_visits_report", function ($scope, $rootScope, $timeout) {
    $scope.from = null;
    $scope.to = null;
    $scope.visitor = null;
    $scope.cm = null;
    $scope.visits = null;
    $scope.visitor_list = null;
    $scope.cm_list = null;
    $scope.submitChange = function () {
        $.getJSON("/admin/reports/visits?v=" + $scope.visitor + "&cm=" + $scope.cm + "&to=" + window.__to_date + "&from=" + window.__from_date, function (data) {
            for (i = 0; i < data.length; i++) {
                $scope.visits.push(data[i]);
                $scope.$apply();
                window.setTimeout(function () {
                    $("select").trigger("change");
                }, 200);
            }
        });
    };
    $.getJSON("/admin/visitor", function (data) {
        $scope.visitor_list = data;
        $scope.$apply();
    });
    $.getJSON("/admin/cm", function (data) {
        $scope.cm_list = data;
        $scope.$apply();
    });

});
app.controller("admin_orders", function ($scope, $rootScope, $timeout) {
    $scope.orders = null;
    $.getJSON("/admin/requests", function (data) {
        $scope.orders = data;
        $scope.$apply();
    });
});
app.controller("admin_manage_visitors", function ($scope, $rootScope, $timeout) {
    $scope.visitors = [];
    $.getJSON("/admin/visitor", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.visitors.push(data[i]);
            $scope.$apply();
        }
    });

    $scope.enableVisitor = function (id, index) {
        $.getJSON("/admin/visitor/" + id + "/enable", function (data) {
            $scope.visitors[index].enabled = true;
            $scope.$apply();
        });
    };
    $scope.disableVisitor = function (id, index) {
        $.getJSON("/admin/visitor/" + id + "/disable", function (data) {
            $scope.visitors[index].enabled = false;
            $scope.$apply();
        });
    }
});
app.controller("admin_manage_cms", function ($scope, $rootScope, $timeout) {
    $scope.cms = [];
    $.getJSON("/admin/cm", function (data) {
        for (i = 0; i < data.length; i++) {
            $scope.cms.push(data[i]);
            $scope.$apply();
        }
    });

    $scope.enableCm = function (id, index) {
        $.getJSON("/admin/cm/" + id + "/enable", function (data) {
            $scope.cms[index].enabled = true;
            $scope.$apply();
        });
    };
    $scope.disableCm = function (id, index) {
        $.getJSON("/admin/cm/" + id + "/disable", function (data) {
            $scope.cms[index].enabled = false;
            $scope.$apply();
        });
    }
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
app.controller("new_city", function ($scope, $rootScope, $timeout, $http) {
    $scope.city = null;
    $scope.newCity = function () {
        formData = new FormData();
        formData.append("name", $scope.city)
        $http({
            method: 'POST',
            url: '/admin/city/new',
            data: formData,
            headers: {
                'Content-Type': undefined
            }
        }).then(function (data) {
            alert(data);
        });
    }
});
function makeActive(ele) {
    ele = $(ele);
    $(".right-menu li").removeClass("active");
    ele.addClass("active");
}