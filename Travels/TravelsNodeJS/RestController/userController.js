const userUtils = require('../Utils/userUtils');

module.exports = {

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