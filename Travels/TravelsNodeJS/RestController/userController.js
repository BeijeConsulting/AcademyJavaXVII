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
    }

}