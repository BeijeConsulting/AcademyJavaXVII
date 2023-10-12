const companyUtils = require('../Utils/companyUtils');

module.exports = {
    /*
    getAllCountries: function () {
        return new Promise(async (resolve, reject) => {
            try {
                const countries = await countryUtils.getAllCountries();
                const countryPromises = countries.map(async (country) => {
                    const cities = await cityUtils.getCityByCountry(country.id);
                    country.cities = cities;
                });

                await Promise.all(countryPromises);
                resolve(countries);
            } catch (error) {
                reject(error);
            }
        });
    },*/

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
    }
}