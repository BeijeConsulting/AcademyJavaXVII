const cityUtils = require('../Utils/cityUtils');
const countryUtils = require('../Utils/countryUtils');


module.exports = {
    getAllCountries: function () {
        return new Promise((resolve, reject) =>{
            countryUtils.getAllCountries().then(countries => {

                for (let i = 0; i < countries.length; i++) {
                    cityUtils.getCityByCountry(countries[i].id).then(cities => {
                        countries[i].cities = cities;
                        //console.log(countries[i]);
                    })
    
                }
    
                return countries;
                //console.log(countries);
            }).then(countries => { console.log(countries); resolve(countries);})
            .catch(err => reject(err));
        })
        
    }


}