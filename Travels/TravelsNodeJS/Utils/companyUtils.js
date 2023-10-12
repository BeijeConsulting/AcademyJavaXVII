const { json } = require("express");
const myModule = require("../mysql");

let connection = myModule.getConnection();

module.exports = {

    getAllCompanies: function () {
        return new Promise((resolve, reject) => {
          connection.query("SELECT * FROM companies", (err, rows, fields) => {
            if (err) {
              reject(err);
            } else {
              resolve(rows);
            }
          });
        });
    },


    getCompanyById: function(id){
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM companies WHERE id = ?", [id], (err, rows, fields) => {
              if (err) {
                reject(err);
              } else {
                resolve(rows[0]);
              }
            });
        });
    },

    
    //add company

    getListByIds: function(ids) {
      return new Promise((resolve, reject) => {
          let companies = [];
          let promises = [];
  
          for (let id of ids) {
              const promise = new Promise((resolve, reject) => {
                  connection.query("SELECT * FROM companies WHERE id = ?", [id], (err, rows, fields) => {
                      if (err) {
                          reject(err);
                      } else {
                          companies.push(rows[0]);
                          resolve();
                      }
                  });
              });
              promises.push(promise);
          }
  
          // Wait for all promises to resolve
          Promise.all(promises)
              .then(() => {
                  resolve(companies);
              })
              .catch(reject);
      });
  },


    //disable company

    //enable company

    getAllDisabledCompanies: function () {
        const today = new Date();
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM companies WHERE disabled_date <= ?", [today], (err, rows, fields) => {
            if (err) {
              reject(err);
            } else {
              resolve(rows);
            }
          });
        });
    },

    getAllEnabledCompanies: function () {
        const today = new Date();
        return new Promise((resolve, reject) => {
            connection.query("SELECT * FROM companies WHERE disabled_date >= ? OR disabled_date IS NULL", [today], (err, rows, fields) => {
            if (err) {
              reject(err);
            } else {
              resolve(rows);
            }
          });
        });
    },

    getCompanyByName: function(name) {
      return new Promise((resolve, reject) => {
          connection.query("SELECT * FROM companies WHERE name = ?", [name] , (err, rows, fields) => {
              if (err) {
                  reject(err);
              } else {
                  resolve(rows[0]);
              }
          });
      });
  },
  


    getCompaniesByIds: function(idsString){
        return new Promise((resolve, reject) => {
            let idsList = idsString.split("_");
            let idsInteger = idsList.map(str => parseInt(str, 10));
            console.log("array numers : ", idsInteger);
            let companies = this.getListByIds(idsInteger);
            console.log("companies : ", companies);
            resolve(companies);
        });
    },

    getCompaniesIdFromList: function(companies){
        return new Promise((resolve, reject) => {
            let idsString = "";
            for (let company of companies){
                idsString += "_" + company.id;
            }
            resolve(idsString.substring(1) );
        });
    },

    addCompany: function(name){
      return new Promise((resolve, reject) => {
        connection.query("INSERT INTO companies (`name`) VALUES (?)", [name] , (err, rows, fields) => {
            if (err) {
                reject(err);
            } else {
                resolve(true);
            }
        });
    });
    }
    
}