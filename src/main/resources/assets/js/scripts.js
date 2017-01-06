var eb = new EventBus("http://localhost:8080/eventbus");
var msg1 = {
	"msg" : ""
};

eb.onopen = function() {
	eb.send("send-msg", JSON.stringify(msg1), function(err1, reply1) {
		var msg_json = JSON.parse(reply1.body);
		$('#test').text(JSON.stringify(msg_json.context));
		$('#text').append(
				'<span class="watson">' + msg_json.output.text + '</span>');
	});
}

function sendChat() {
	$('#text').append(
			'<span class="customer">' + $('#chatText').val() + '</span>');
	$('#chatText').val(' ');
	msg1.context = $('#test').text();
	msg1.msg = $('#chatText').val();
	eb.send("send-msg", JSON.stringify(msg1), function(err, reply) {
		var msg_json = JSON.parse(reply.body);
		$('#text').append(
				'<span class="watson">' + msg_json.output.text + '</span>');
		$('#test').text(JSON.stringify(msg_json.context));
		switch (msg_json.intents[0].intent) {
		case "dispute_charge":
			setTimeout(function() {
				$('#disputeModal').modal('show');
			}, 1500);
			break;
		case "report_fraud":
			setTimeout(function() {
				$('#fraudModal').modal('show');
			}, 1500);
			break;
		case "goodbye":
			setTimeout(function() {
				$('#surveyModal').modal('show');
			},1500);
			break;
		}
	});

}

function sendDispute() {
	$('#myPleaseWait').modal('show');
	setTimeout(
			function() {
				$('#myPleaseWait').modal('hide');
				$('#text')
						.append(
								'<span class="watson">Your information has been processed. You will be contacted within five (5) business days.</span>');
			}, 3000);

}