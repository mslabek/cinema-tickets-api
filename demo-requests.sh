#!/bin/bash

echo -e "\n\t1. User checks all available movies in the cinema"
echo -e "\tSending GET to http://localhost:8080/movies\n"
curl -v "localhost:8080/movies" | python -m json.tool

echo -e "\n\t2. User checks all screenings that begin between 10:20 and 14:35"
echo -e "\tSending GET to http://localhost:8080/screenings?begins-after=2022-12-30T10:20:00&begins-before=2022-12-30T14:35:00\n"
curl -v "http://localhost:8080/screenings?begins-after=2022-12-30T10:20:00&begins-before=2022-12-30T14:35:00" | python -m json.tool

echo -e "\n\t3. User chooses screening with id 2 and checks details about it to see what seats are free"
echo -e "\tSending GET to http://localhost:8080/screenings/2\n"
curl -v "http://localhost:8080/screenings/2" | python -m json.tool | head -n 38

echo -e "\n\t4. User chooses 2 seats, with ids of 200 (row 1, column 1) and 201 (row 1, column 2) and sends a reservation request"
echo -e "\tSending POST to http://localhost:8080/reservations"
curl -v --header "Content-Type: application/json" \
             --data '
             {
                    "screeningId": 2,
                    "name": "Świętek",
                    "surname": "Schäfer",
                    "tickets": [
                        {
                            "seatId": 201,
                            "type": "STUDENT"
                        },
                        {
                            "seatId": 202,
                            "type": "ADULT"
                        }
                    ]
            }' \
             "http://localhost:8080/reservations" | python -m json.tool | head -n 38


echo -e "\n\t5. User performs a payment"
echo -e "\tSending PATCH to http://localhost:8080/reservations/1"
curl -v --header "Content-Type: application/json" \
            --request PATCH \
             --data '
            {
                "amount": 43,
                "currency": "PLN"
            }' \
             "http://localhost:8080/reservations/1" | python -m json.tool | head -n 38
