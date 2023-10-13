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
    }

}