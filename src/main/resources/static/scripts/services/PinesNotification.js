'use strict';

angular
    .module('otd.services.pinesNotifications', [])
    .factory('PinesNotifications', function () {
    return {
        notify: function (args) {
            var notification = new PNotify(args);
            notification.notify = notification.update;
            return notification;
        }
    }
})