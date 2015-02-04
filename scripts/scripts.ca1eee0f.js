"use strict";angular.module("MayDayApp",["ngAnimate","ngCookies","ngResource","ngRoute","ngSanitize","ngTouch","duParallax"]).run(["$rootScope","$location","parallaxHelper",function(a,b,c){a.navigation=[{page:"Home",path:""},{page:"About",path:"about"},{page:"Technology",path:"technology"},{page:"Privacy",path:"privacy"},{page:"Contact",path:"contact"}],a.navClass=function(a){a=a.toLowerCase();var c=b.path().substring(1)||"home";return c=c.toLowerCase(),a===c?"active":""},a.background=c.createAnimator(-.3),a.invertColors=function(a){console.dir(a);var b=-.4,c=Math.min(Math.max(a.elemY*b,0),255),d=255-c;return{backgroundColor:"rgb("+d+", "+d+", "+d+")",color:"rgb("+c+", "+c+", "+c+")"}},a.transitionBackground=function(a){var b=a.elemY*-.6;return{backgroundPosition:b+"px 0px "}},a.grow=function(a){return a.elemY<300&&a.elemY>50?{transform:"scale(1.2)"}:{transform:"scale(1)"}}}]).filter("reverse",function(){return function(a){return a.slice().reverse()}}).config(["$routeProvider",function(a){a.when("/",{templateUrl:"views/main.html",controller:"MainCtrl"}).when("/about",{templateUrl:"views/about.html",controller:"AboutCtrl"}).when("/contact",{templateUrl:"views/contact.html",controller:"ContactCtrl"}).when("/technology",{templateUrl:"views/technology.html",controller:"TechnologyCtrl"}).when("/privacy",{templateUrl:"views/privacy.html",controller:"PrivacyCtrl"}).otherwise({redirectTo:"/"})}]),angular.module("MayDayApp").controller("MainCtrl",["$scope",function(a){a.awesomeThings=["HTML5 Boilerplate","AngularJS","Karma"]}]),angular.module("MayDayApp").controller("AboutCtrl",["$scope",function(a){a.awesomeThings=["HTML5 Boilerplate","AngularJS","Karma"]}]),angular.module("MayDayApp").controller("ContactCtrl",["$scope",function(a){a.awesomeThings=["HTML5 Boilerplate","AngularJS","Karma"]}]),angular.module("MayDayApp").controller("TechnologyCtrl",["$scope",function(a){a.awesomeThings=["HTML5 Boilerplate","AngularJS","Karma"]}]),angular.module("MayDayApp").controller("PrivacyCtrl",["$scope",function(a){a.awesomeThings=["HTML5 Boilerplate","AngularJS","Karma"]}]);