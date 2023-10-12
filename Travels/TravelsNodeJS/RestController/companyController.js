const companyUtils = require('../Utils/companyUtils');

module.exports = {

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
    }
}