const cityUtils = require('../Utils/cityUtils');
const countryUtils = require('../Utils/countryUtils');
const xportUtils = require('../Utils/xportUtils');


module.exports = {
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
    },

    getAllCities: function(){
        return cityUtils.getAllCities();
    },

    getCityById: function(id){
        return new Promise(async (resolve, reject) => {
            try {
                let city = await cityUtils.getCityById(id);
                const xportsPromises = await xportUtils.getXportsByCity(id);
                const xports = await Promise.all(xportsPromises);
                city.xports = xports;
                
                console.log(city);
                resolve(city);
            } catch (error) {
                reject(error);
            }
        });
    }

}
