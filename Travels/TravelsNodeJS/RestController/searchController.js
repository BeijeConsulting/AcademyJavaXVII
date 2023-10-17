const searchUtils = require('../Utils/searchUtils');

module.exports = {
    // da finire
    searchTravels: function (data) {
        return new Promise(async (resolve, reject) => {

            try {
                let obj;
                let departure_date = data.departure_date;
                let departure_city = data.departure_city;
                let arrival_city = data.arrival_city;
                let passengers = data.passengers_number;
                let departure = await searchUtils.searchTravels(departure_date, departure_city, arrival_city, passengers)
                console.log("DATA", data)

                if (data.only_depart === 'on') {
                    obj = {
                        departure_schedule: departure,
                    }
                } else {
                    let return_date = data.return_date;
                    let arrival = await searchUtils.searchTravels(return_date, arrival_city, departure_city, passengers)
                    obj = {
                        departure_schedule: departure,
                        arrival_schedule: arrival
                    }
                }
                console.log("OBJ", obj)
                resolve(obj)
                
            } catch (error) {
                reject(error);
            }


        })
    }


}