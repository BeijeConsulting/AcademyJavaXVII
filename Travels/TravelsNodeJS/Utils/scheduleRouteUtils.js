const myModule = require("../mysql");
const dayOfWeek = require("./dayOfWeekUtils");
const xportUtils = require("./xportUtils");
const cityUtils = require("./cityUtils");

let connection = myModule.getConnection();

module.exports = {
  getAllSchedulesByRoutes: function (routes, date) {
    let newSchedules = array();
    let day = date.dayOfMonth;
    let month = date.monthValue;
    let year = date.year;
    let stringDate = year + "-" + month + "-" + day; //2023-9-21
    let dayOfWeek;

    switch (date.dayOfWeek) {
      case "MONDAY":
        dayOfWeek = 1;
        break;
      case "TUESDAY":
        dayOfWeek = 2;
        break;
      case "WEDNESDAY":
        dayOfWeek = 3;
        break;
      case "THURSDAY":
        dayOfWeek = 4;
        break;
      case "FRIDAY":
        dayOfWeek = 5;
        break;
      case "SATURDAY":
        dayOfWeek = 6;
        break;
      case "SUNDAY":
        dayOfWeek = 0;
        break;
    }

    this.getAllSchedulesByRoutes1(routes, stringDate).then((schedules) => {
      for (let i = 0; i < schedules.length; i++) {
        dayOfWeek.getDaysOfWeekBySchedule(schedules[i]).then((days) => {
          for (let j = 0; j < days.length; j++) {
            if (days[j].day == dayOfWeek) {
              newSchedules.push(schedules[i]);
            }
          }
        });
      }
      return newSchedules;
    });
  },
  getAllSchedulesByRoutes1: function (routes, stringDate) {
    return new Promise((resolve, reject) => {
      connection.query(
        "SELECT * FROM schedules WHERE route_id = ? AND start_date <= ? AND (end_date is null or end_date >= ?)",
        [routes.id, stringDate, stringDate],
        (err, rows, fields) => {
          if (err) {
            reject(err);
          } else {
            resolve(rows);
          }
        }
      );
    });
  },
  getScheduleById: function (id) {
    return new Promise(
      (resolve, reject) => {
        connection.query(
          "SELECT * FROM schedules WHERE id = ?",
          [id],
          (err, rows, fields) => {
            if (err) {
              reject(err);
            } else {
              resolve(rows[0]);
            }
          }
        );
      });
  },
  getScheduleByRouteId: function (routeId) {
    return new Promise(
      (resolve, reject) => {
        connection.query(
          "SELECT * FROM schedules WHERE route_id = ?",
          [routeId],
          (err, rows, fields) => {
            if (err) {
              reject(err);
            } else {
              resolve(rows);
            }
          }
        );
      })
  },
  getSchedulesByRouteOnlyActive: function (routeId) {
    //prendo la data di oggi
    const currentDate = new Date().toISOString().slice(0, 10);
    return new Promise((resolve, reject) => {
      connection.query(
        "SELECT * FROM schedules WHERE route_id = ? AND (end_date is null or end_date <= ?)",
        [routeId, currentDate],
        (err, rows, fields) => {
          if (err) {
            reject(err);
          } else {
            resolve(rows);
          }
        }
      );
    });
  },

  getSchedulesByRouteAndDayAndValidity: function (
    routeId,
    days,
    departureDate
  ) {
    //days e' una lista di interi che rappresentano i giorni della settimana

    return new Promise((resolve, reject) => {
      connection.query(
        "SELECT DISTINCT s1.* FROM schedules AS s1 " +
          "INNER JOIN days_of_week AS dow ON dow.schedule_id = s1.id " +
          "WHERE s1.route_id = ? AND dow.day IN (?) AND start_date <= ? AND end_date >= ?",
        [routeId, days, departureDate, departureDate],
        (err, rows, fields) => {
          if (err) {
            reject(err);
          } else {
            let schedules = [];
            for (let i = 0; i < rows.length; i++) {
              dayOfWeek.getDaysOfWeekBySchedule(rows[i]).then((days) => {
                for (let j = 0; j < days.length; j++) {
                  if (days[j].day == dayOfWeek) {
                    schedules.push(rows[i]);
                  }
                }
              });
            }
            resolve(schedules);
          }
        }
      );
    });
  },
  getSchedulesByIds: function (ids) {
    return new Promise((resolve, reject) => {
      connection.query(
        "SELECT * FROM schedules WHERE id IN (?)",
        [ids],
        (err, rows, fields) => {
          if (err) {
            reject(err);
          } else {
            resolve(rows);
          }
        }
      );
    });
  },
  getSchedulesIdFromList: function (schedules) {
    let ids = [];
    for (let i = 0; i < schedules.length; i++) {
      ids.push(schedules[i].id);
    }
    return ids;
  },

  getAllRoutes: function () {
    return new Promise((resolve, reject) => {
      connection.query("SELECT * FROM routes", (err, rows, fields) => {
        if (err) {
          reject(err);
        } else {
          resolve(rows);
        }
      });
    });
  },
  getRouteById: function (id) {
    return new Promise((resolve, reject) => {
      connection.query(
        "SELECT * FROM routes WHERE id = ?",
        [id],
        (err, rows, fields) => {
          if (err) {
            reject(err);
          } else {
            resolve(rows[0]);
          }
        }
      );
    });
  },
  getAllRoutesByCityNameLike: function (departure_city, arrival_city) {
    //mi servono gli id delle due citta passate
    //poi devo trovare gli xport che hanno quei city id
    //poi devo trovare le routes che hanno quegli xport id e passarsi alla query
    return new Promise((resolve, reject) => {
      const departureCity = cityUtils.getCityByName(departure_city);
      const arrivalCity = cityUtils.getCityByName(arrival_city);

      const departureCityId = departureCity.id;
      const arrivalCityId = arrivalCity.id;

      const departureXport = xportUtils.getXportById(departureCityId);
      const arrivalXport = xportUtils.getXportById(arrivalCityId);

      const departureXportId = departureXport.id;
      const arrivalXportId = arrivalXport.id;
      connection.query(
        "SELECT * FROM routes WHERE departure_xport_id IN (?) AND arrival_xport_id IN (?)'",
        [departureXportId, arrivalXportId],
        (err, rows, fields) => {
          if (err) {
            reject(err);
          } else {
            resolve(rows);
          }
        }
      );
    });
  },
  getAllRoutesByXportNameLike: function (xportName) {
    return new Promise((resolve, reject) => {
      xportUtils.getXportsByName(xportName).then((xports) => {
        const xportIds = xportUtils.getXportsIdFromList(xports);

        if (xportIds.length === 0) {
          //torna un array vuoto
          resolve([]);
          return;
        }

        const query =
          "SELECT * FROM routes WHERE departure_xport_id IN (?) and arrival_xport_id IN (?)";
        connection.query(query, [xportIds, xportIds], (err, rows) => {
          if (err) {
            reject(err);
          } else {
            resolve(rows);
          }
        });
      });
    });
  },
  getRoutesByDepartureCityName: function (departure_city) {
    return new Promise((resolve, reject) => {
      //devo prendere il nome della citta da cituUtils e poi il suo id da cityUtils
      cityUtils.getCityByName(departure_city).then((city) => {
        if (!city) {
          // La città non è stata trovata, quindi non ci sono rotte corrispondenti
          resolve([]);
          return;
        }
        xportUtils.getXportById(city.id).then((xport) => {
          for (let i = 0; i < xport.length; i++) {
            this.getAllRoutesByXportNameLike(xport.name).then((routes) => {
              resolve(routes);
            });
          }
        });
      });
    });
  },
  getRoutesByArrCityName: function (arrival_city) {
    return new Promise((resolve, reject) => {
      //devo prendere il nome della citta da cituUtils e poi il suo id da cityUtils
      cityUtils.getCityByName(arrival_city).then((city) => {
        if (!city) {
          // La città non è stata trovata, quindi non ci sono rotte corrispondenti
          resolve([]);
          return;
        }
        xportUtils.getXportById(city.id).then((xport) => {
          for (let i = 0; i < xport.length; i++) {
            this.getAllRoutesByXportNameLike(xport.name).then((routes) => {
              resolve(routes);
            });
          }
        });
      });
    });
  },
};
