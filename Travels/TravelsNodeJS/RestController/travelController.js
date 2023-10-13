const travelUtils = require('../Utils/travelUtils');
const xportUtils = require('../Utils/xportUtils');
const scheduleRouteUtils = require('../Utils/scheduleRouteUtils');

module.exports = {
    getAllTravels: function () {
        return new Promise(async (resolve, reject) => {
            try {
                const travels = await travelUtils.getAllTravels();
                const travelPromises = travels.map(async (travel) => {
                    let route = await scheduleRouteUtils.getRouteById(travel.route_id);
                    travel.route = route;
                    let departureXport = await xportUtils.getXportById(route.departure_xport_id);
                    let arrivalXport = await xportUtils.getXportById(route.arrival_xport_id);
                    travel.route.arrivalXport = arrivalXport;
                    travel.route.departureXport = departureXport;

                    let schedule = await scheduleRouteUtils.getScheduleById(travel.schedule_id);
                    travel.schedule = schedule;
                    //console.log(departureXport);
                });

                await Promise.all(travelPromises);
                //console.log(travels);
                resolve(travels);
            } catch (error) {
                reject(error);
            }
        });
    },
}