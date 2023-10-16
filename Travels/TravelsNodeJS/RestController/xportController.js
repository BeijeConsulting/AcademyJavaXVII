const xportUtils = require('../Utils/xportUtils');

module.exports = {
    getAllXports: function(){
        return xportUtils.getAllXports();
    },
    addXport: function(data) {
        return xportUtils.addXport(data);
    },
    editXport: function(name, id) {
        return xportUtils.editXport(name, id);
    }
}