Rickshaw.namespace('Rickshaw.Fixtures.RandomData');

Rickshaw.Fixtures.RandomData = function(timeInterval) {

	var addData;
	timeInterval = timeInterval || 1;

	var lastRandomValue = 200;

	var timeBase = Math.floor(new Date().getTime() / 1000);

	this.addData = function(data) {

		var randomValue = Math.random() * 100 + 15 + lastRandomValue;
		var index = data[0].length;

		data.forEach( function(series) {
			var jsonData = $.ajax({
              url: "rest/resource/getReceived",
              dataType:"json",
              async: false
              }).responseText;  
			series.push( { x: (index * timeInterval) + timeBase, y: parseInt(jsonData) } );
		} );

		lastRandomValue = randomValue * .85;
		
	}
};

