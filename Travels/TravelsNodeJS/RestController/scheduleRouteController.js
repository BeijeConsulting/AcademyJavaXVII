const companyUtils = require('../Utils/companyUtils');
const scheduleRouteUtils = require('../Utils/scheduleRouteUtils');
const xportUtils = require('../Utils/xportUtils');
const utils = require('../Utils/utils');

module.exports = {
    getAllRoutes: function(){
        return new Promise(async (resolve, reject) =>{
            try{
                let routes = await scheduleRouteUtils.getAllRoutes();
                const routesPromise = routes.map(async (route) =>{
                    let departureXport = await xportUtils.getXportById(route.departure_xport_id);
                    route.departureXport = departureXport;
                    let arrivalXport = await xportUtils.getXportById(route.arrival_xport_id);
                    route.arrivalXport = arrivalXport;

                });
                await Promise.all(routesPromise);
                resolve(routes);

            } catch (error) {
                reject(error);
            }

        });
    },

    getRouteById: function(id){
        return scheduleRouteUtils.getRouteById(id);
    },

    getSchedulesByRouteId: function(route_id){
        return new Promise(async(resolve, reject) =>{
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
            
        });
    }

    /*
    getAllCompanies: function(){
        return companyUtils.getAllCompanies();
    },

    getAllDisabledCompanies: function (){
        return companyUtils.getAllDisabledCompanies();
    },

    getAllEnabledCompanies: function (){
        return companyUtils.getAllEnabledCompanies();
    },

    addCompany: function(name){
        return companyUtils.addCompany(name); 
    },

    enableCompany : function(id){
        return companyUtils.enableCompany(id);
    },

    disableCompany : function(id){
        return companyUtils.disableCompany(id); 
    }*/


}