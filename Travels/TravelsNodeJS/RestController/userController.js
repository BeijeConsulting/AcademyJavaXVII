const userUtils = require('../Utils/userUtils');
const userAuthorityUtils = require('../Utils/userAuthorityUtils');

module.exports = {

    getUserById: function(id) {
        return userUtils.getUserById(id);
    },

    getUserByEmail: function(email) {
        return userUtils.getUserByEmail(email);
    },

    getAllAdmin: function() {
        return userAuthorityUtils.getAllAdmins();
    },

    getAllCustomers: function() {
        return userAuthorityUtils.getAllCustomers();
    },

    getUserById: function(id){
        return userUtils.getUserById(id);
    },

    editUserDetails: function(id,name,surname){
        return userUtils.editUserDetails(id,name,surname)
    },

    editUserPassword: function(id, oldPw, newPw){ 
        return userUtils.editUserPassword(id,oldPw,newPw)
    },

    disableUser: function(id){
        return userUtils.disableUser(id);
    }
}