demo = {
    initGoogleMaps: function () {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                const pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                const myLatlng = new google.maps.LatLng(pos.lat, pos.lng);
                const mapOptions = {
                    zoom: 15,
                    center: myLatlng,
                    scrollwheel: true,
                    styles: [
                        {
                            "elementType": "geometry",
                            "stylers": [{
                                "color": "#f5f5f5"
                            }]
                        },
                        {
                            "elementType": "labels.icon",
                            "stylers": [{
                                "visibility": "off"
                            }]
                        },
                        {
                            "elementType": "labels.text.fill",
                            "stylers": [{
                                "color": "#616161"
                            }]
                        },
                        {
                            "elementType": "labels.text.stroke",
                            "stylers": [{
                                "color": "#f5f5f5"
                            }]
                        },
                        {
                            "featureType": "administrative.land_parcel",
                            "elementType": "labels.text.fill",
                            "stylers": [{
                                "color": "#bdbdbd"
                            }]
                        },
                        {
                            "featureType": "poi",
                            "elementType": "geometry",
                            "stylers": [{
                                "color": "#eeeeee"
                            }]
                        },
                        {
                            "featureType": "poi",
                            "elementType": "labels.text.fill",
                            "stylers": [{
                                "color": "#757575"
                            }]
                        },
                        {
                            "featureType": "poi.park",
                            "elementType": "geometry",
                            "stylers": [{
                                "color": "#e5e5e5"
                            }]
                        },
                        {
                            "featureType": "poi.park",
                            "elementType": "labels.text.fill",
                            "stylers": [{
                                "color": "#9e9e9e"
                            }]
                        },
                        {
                            "featureType": "road",
                            "elementType": "geometry",
                            "stylers": [{
                                "color": "#ffffff"
                            }]
                        },
                        {
                            "featureType": "road.arterial",
                            "elementType": "labels.text.fill",
                            "stylers": [{
                                "color": "#757575"
                            }]
                        },
                        {
                            "featureType": "road.highway",
                            "elementType": "geometry",
                            "stylers": [{
                                "color": "#dadada"
                            }]
                        },
                        {
                            "featureType": "road.highway",
                            "elementType": "labels.text.fill",
                            "stylers": [{
                                "color": "#616161"
                            }]
                        },
                        {
                            "featureType": "road.local",
                            "elementType": "labels.text.fill",
                            "stylers": [{
                                "color": "#9e9e9e"
                            }]
                        },
                        {
                            "featureType": "transit.line",
                            "elementType": "geometry",
                            "stylers": [{
                                "color": "#e5e5e5"
                            }]
                        },
                        {
                            "featureType": "transit.station",
                            "elementType": "geometry",
                            "stylers": [{
                                "color": "#eeeeee"
                            }]
                        },
                        {
                            "featureType": "water",
                            "elementType": "geometry",
                            "stylers": [{
                                "color": "#c9c9c9"
                            }]
                        },
                        {
                            "featureType": "water",
                            "elementType": "labels.text.fill",
                            "stylers": [{
                                "color": "#9e9e9e"
                            }]
                        }
                    ]

                };
                const map = new google.maps.Map(document.getElementById("map"), mapOptions);

                // Create challenge
                const createChallengeWindow = new google.maps.InfoWindow;
                let mousedUp = false;
                map.addListener('mousedown', function (event) {
                    mousedUp = false;
                    setTimeout(function () {
                        if (mousedUp === false) {
                            const latitude = event.latLng.lat();
                            const longitude = event.latLng.lng();
                            const a = document.createElement('a');
                            const linkText = document.createTextNode(createChallenge);
                            a.appendChild(linkText);
                            a.style.color = "black";
                            a.href = "/challenge/create?latCoords=" + latitude + "&lngCoords=" + longitude;
                            document.body.appendChild(a);
                            createChallengeWindow.setContent(a);
                            createChallengeWindow.setPosition({lat: latitude, lng: longitude});
                            createChallengeWindow.open(map);
                        }
                    }, 500);
                });
                map.addListener('mouseup', function () {
                    mousedUp = true;
                });
                map.addListener('dragstart', function () {
                    mousedUp = true;
                });

                const avatarIcon = {
                    url: "/img/avatars/Default.png", // url
                    scaledSize: new google.maps.Size(50, 50), // scaled size
                    origin: new google.maps.Point(0, 0), // origin
                    anchor: new google.maps.Point(0, 0) // anchor
                };
                const avatarMarker = new google.maps.Marker({
                    position: new google.maps.LatLng(pos.lat, pos.lng), // position
                    //map: map, // same as avatarMarker.setMap(map);
                    icon: avatarIcon, // icon of marker
                    title: "Zde se nacházíš!"
                });
                avatarMarker.setMap(map);

                const infowindow = new google.maps.InfoWindow;

                // individual challenges
                for (let i = 0; i < challenges.length; i++) {
                    // Without this empty wrapper functions, it shows markers randomly, see:
                    // https://stackoverflow.com/questions/25638834/mutable-variable-is-accessible-from-closure/25638959
                    (function () {
                        const icon = {
                            url: "/img/activities/" + challenges[i][10] + ".png", // url
                            scaledSize: new google.maps.Size(50, 50), // scaled size
                            origin: new google.maps.Point(0, 0), // origin
                            anchor: new google.maps.Point(0, 0) // anchor
                        };

                        const marker = new google.maps.Marker({
                            position: new google.maps.LatLng(challenges[i][0], challenges[i][1]),
                            map: map,
                            icon: icon
                        });

                        // Default icon of challenge.
                        const defaultIcon = {
                            url: "/img/activities/Default.png",
                            scaledSize: new google.maps.Size(50, 50), // scaled size
                            origin: new google.maps.Point(0, 0), // origin
                            anchor: new google.maps.Point(0, 0) // anchor
                        };
                        // Challenge icon if is defined.
                        const ic = new Image();
                        ic.src = "/img/activities/" + challenges[i][10] + ".png";

                        ic.onload = function () {
                            marker.setIcon(icon); // If icon found, go ahead and show it.
                        };
                        ic.onerror = function () {
                            //https://stackoverflow.com/questions/25058443/google-maps-api-v-3-use-default-marker-icon-if-not-found-at-url
                            marker.setIcon(defaultIcon); // This displays default marker icon in case image is not found.
                        };

                        // Pop up window of challenge(marker), if its clicked.
                        google.maps.event.addListener(marker, 'click', (function (marker, i) {
                            return function () {
                                infowindow.setContent(
                                    challenges[i][2] + "<br/>" +
                                    challenges[i][3] + "<br/>" +
                                    challenges[i][4] + "<br/>" +
                                    challenges[i][5] + "<br/>" +
                                    challenges[i][6] + "<br/>" +
                                    challenges[i][7] + "<br/>" +
                                    challenges[i][11] + "<br/>" + "<br/>" +
                                    '<a style="color:black" href=/challenge/detail?challengeId=' + challenges[i][9] + '>' + challenges[i][8] + '</a>'
                                );
                                infowindow.open(map, marker);
                            }
                        })(marker, i));
                    })();
                }
            });
        }
    }
};
