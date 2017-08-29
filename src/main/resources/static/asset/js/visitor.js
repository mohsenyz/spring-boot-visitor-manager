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
                templateUrl: "reports.html"
            })
            .when("/received-visit", {
                templateUrl: "received_visits.html"
            })
            .when("/next-visit", {
                templateUrl: "next_visists.html"
            })
            .when("/forwarded-visit", {
                templateUrl: "forwarded_visits.html"
            })
            .when("/user", {
                templateUrl: "user_visitor.html",
                controller: "user"
            })
            .when("/curr", {
                templateUrl: "curr.html"
            })
            .when("/new", {
                templateUrl: "new_visit.html",
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
            $("select").select2({
                dir: "rtl"
            });
        }, 0);
    };
    $rootScope.$on("$includeContentLoaded", function (event, templateName) {
        $rootScope.reInit();
    });
});
app.controller("new_visit", function ($scope, $rootScope, $timeout, $http) {
    $scope.result_drugs = [];
    $scope.content_drugs = [];
    $scope.docs = [];
    $scope.visitor = 'none';
    $scope.doctor = 'none';
    $scope.drugstore = 'none';
    $scope.content = null;
    $scope.desc = null;
    $scope.noskhe = [];
    $scope.not_noskhe = [];
    $scope.known_drugs = [];
    $scope.exists_drugs = [];
    $scope.drugs_esales = [];
    $scope.same_drugs = [];
    $scope.next_session_date = null;
    $scope.visit_time = null;
    $scope.visit_date = null;
    $scope.urgency = null;
    $scope.ds = {
        ds_name: null,
        ds_fanni_name: null,
        phone_number: null,
        address: null,
        knowledge: null,
        best_visit_time: null,
        type: null,
        company_name_ack: null
    };
    $scope.dr = {
        name: null,
        expertise: null,
        best_visit_time: null,
        fixed_phone: null,
        mobile: null,
        place: null,
        address: null,
        email: null,
        pezeshk: null,
        company_products_ack: null,
        consent: null
    };
    $scope.visit_place = null;
    $scope.visit_place_name = null;
    $scope.dr_visit_suggestion = null;
    $scope.ds_visited_name = null;
    $scope.ds_visited_exp = null;
    $scope.ds_visited_phone = null;
    $scope.ds_idea = null;
    $scope.ds_pop_ds_name = null;
    $scope.ds_opponent = null;
    $scope.ds_dr_index = null;
    $scope.cm = null;
    $scope.given = null;
    $scope.given_etc = null;
    $scope.needed = null;
    $scope.result = null;
    $scope.submitThis = function () {
        dScope = {
            result_drugs: $scope.result_drugs,
            content_drugs: $scope.content_drugs,
            docs: $scope.docs,
            visitor: $scope.visitor,
            doctor: $scope.doctor,
            drugstore: $scope.drugstore,
            content: $scope.content,
            desc: $scope.desc,
            noskhe: $scope.noskhe,
            not_noskhe: $scope.not_noskhe,
            known_drugs: $scope.known_drugs,
            exists_drugs: $scope.exists_drugs,
            drugs_esales: $scope.drugs_esales,
            same_drugs : $scope.same_drugs,
            next_session_date: $scope.next_session_date,
            visit_time: $scope.visit_time,
            visit_date: $scope.visit_date,
            urgency: $scope.urgency,
            ds: {
                ds_name: $scope.ds.ds_name,
                ds_fanni_name: $scope.ds.ds_fanni_name,
                phone_number: $scope.ds.phone_number,
                address: $scope.ds.address,
                knowledge: $scope.ds.knowledge,
                best_visit_time: $scope.ds.best_visit_time,
                type: $scope.ds.type,
                company_name_ack: $scope.ds.company_name_ack
            },
            dr: {
                name: $scope.dr.name,
                expertise: $scope.dr.expertise,
                best_visit_time: $scope.dr.best_visit_time,
                fixed_phone: $scope.dr.fixed_phone,
                mobile: $scope.dr.mobile,
                place: $scope.dr.place,
                address: $scope.dr.address,
                email: $scope.dr.email,
                pezeshk: $scope.dr.pezeshk,
                company_products_ack: $scope.dr.company_products_ack,
                consent: $scope.dr.consent
            },
            visit_place: $scope.visit_place,
            visit_place_name: $scope.visit_place_name,
            dr_visit_suggestion: $scope.dr_visit_suggestion,
            ds_visited_name: $scope.ds_visited_name,
            ds_visited_exp: $scope.ds_visited_exp,
            ds_visited_phone: $scope.ds_visited_phone,
            ds_idea: $scope.ds_idea,
            ds_pop_ds_name: $scope.ds_pop_ds_name,
            ds_opponent: $scope.ds_opponent,
            ds_dr_index: $scope.ds_dr_index,
            cm: $scope.cm,
            given: $scope.given,
            given_etc: $scope.given_etc,
            needed: $scope.needed,
            result: $scope.result
        };
        var formdata = new FormData();
        for (i = 0; i < dScope.docs.length; i++) {
            name = randomStr(15) + "_" + dScope.docs[i].file.name;
            formdata.append(name, dScope.docs[i].file);
            dScope.docs[i].file = name;
        }
        formdata.append("json", JSON.stringify(JSON.decycle(dScope, true)));
        $http({
            method: 'POST',
            url: '/visitor/visit/new',
            data: formdata,
            headers: {
                'Content-Type': undefined
            }
        }).then(function (data) {
            alert(data);
        });
    }
    ;
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