const cityUtils = require('../Utils/cityUtils');
const countryUtils = require('../Utils/countryUtils');


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
    }
}
