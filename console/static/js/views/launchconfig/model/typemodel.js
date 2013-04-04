define([], function() {
  return Backbone.Model.extend({
    
    initialize: function() {

    },

    validation: {
     /*   
      type_number: {
        required: true,
        pattern: 'number',
        min: 1,
        max: 99,
        msg: 'This field is required, and must be a number between 1 and 99.'
      },
    */
      type_names: {
        required: true,
        msg: 'This field is required.'
      }
    },

    finish: function(outputModel) {
      outputModel.set('name', this.get('type_names'));
      outputModel.set('instance_type', this.get('instance_type'));
    }

  });
});
