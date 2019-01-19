$(function() {

    $('#send-data').click(function() {
        console.log('Clicked!');

        var dataContainer = {
            data: []
        };

        dataContainer.data.push({
            name: 'Joe',
            age: 33
        });

        dataContainer.data.push({
            name: 'Mary',
            age: 18
        });

        console.log("Sending container");
        $.ajax({
            type: 'POST',
            contentType: "application/json",
            url: "/view/container",
            data: JSON.stringify(dataContainer),
            success: function(data) {
                console.log("Sent container!");
            },
            error: function (e) {
                console.log("Error container!");
            }
        });

        console.log("Sending list");
        $.ajax({
            type: 'POST',
            contentType: "application/json",
            url: "/view/list",
            data: JSON.stringify(dataContainer.data),
            success: function(data) {
                console.log("Sent list!");
            },
            error: function (e) {
                console.log("Error list!");
            }
        });
    });
});