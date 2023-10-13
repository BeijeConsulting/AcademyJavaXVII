const companyUtils = require('../Utils/companyUtils');
const scheduleRouteUtils = require('../Utils/scheduleRouteUtils');
const utils = require('../Utils/utils');

module.exports = {
    getAllRoutes: function(){
        return scheduleRouteUtils.getAllRoutes();
    },

    getAllRoutesByCityXportNameLike: function(search_name){
        return scheduleRouteUtils.getAllRoutesByCityXportNameLike(search_name);
    },

    getRouteById: function(id){
        return scheduleRouteUtils.getRouteById(id);
    },

    getSchedulesByRouteId: function(route_id){
        return scheduleRouteUtils.getSchedulesByRouteId(route_id);
        
        /*return new Promise(async(resolve, reject) =>{
            try {
                let schedules = await  scheduleRouteUtils.getScheduleByRouteId(route_id);
                const schedulesPromise = schedules.map(async (schedule) => {
                    let company = await companyUtils.getCompanyById(schedule.company_id);
                    schedule.company_name = company.name;
                    
                    let temp = utils.parseDate(schedule.start_date);
                    schedule.start_date = temp;
                    temp = utils.parseDate(schedule.end_date);
                    schedule.end_date = temp;

                });
                await Promise.all(schedulesPromise);
                resolve(schedules);
            } catch (error){
                reject(error);
            }
            
        });*/
    },

    addRoute: function(type, departure_xport_id, arrival_xport_id){
        if (departure_xport_id === arrival_xport_id) {
            //creo e lancio un errore a mio piacere
            throw new Error('Departure and arrival Xport cannot be the same.'); //questa descrizione apparirà in error.message
        }

        return scheduleRouteUtils.addRoute(type, departure_xport_id, arrival_xport_id);
    }



}