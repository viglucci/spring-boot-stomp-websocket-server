var stompClient = null;
var pingTimeout = null;

function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	if (connected) {
		$("#conversation").show();
	}
	else {
		$("#conversation").hide();
		if (pingTimeout) {
			clearInterval(pingTimeout);
		}
	}
	$("#greetings").html("");
}

function onConnect(frame) {

	setConnected(true);

	console.log('Connected: ', frame);

	stompClient.subscribe('/topic/greetings', function (greeting) {
		showGreeting(JSON.parse(greeting.body).content);
	});

	stompClient.subscribe('/user/queue/pong', function (message) {
		console.log(JSON.parse(message.body).content);
	});

	pingTimeout = setInterval(function () {
		stompClient.send("/app/ping");
	}, 1000);
}

function connect() {
	var socket = new SockJS('/ws');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, onConnect);
}

function disconnect() {
	if (stompClient !== null) {
		stompClient.disconnect();
	}
	setConnected(false);
	console.log("Disconnected");
}

function sendName() {
	stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
	$("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
	$("form").on('submit', function (e) {
		e.preventDefault();
	});
	$( "#connect" ).click(function() { connect(); });
	$( "#disconnect" ).click(function() { disconnect(); });
	$( "#send" ).click(function() { sendName(); });
});