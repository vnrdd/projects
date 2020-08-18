#include <MQTT.h>
#include <PubSubClient.h>
#include <WebServer.h>
#include <WiFi.h>
#include <WiFiClient.h>

#include "content.h"

#define MQTT_client "Coolpad"

const int tempIn = A3;
const int lightIn = A0;
const int ventPin = 32;

WebServer server(80);

int tempRawValue = 0;
int lightRawValue = 0;
double tempVoltage = 0;
double lightVoltage = 0;
int tempC = 0;
double light = 0;
int borderTemp = 55;
int mod = 1;

const char *ssid = "rud39";
const char *password = "aERF8qRCjsJE";

const char *mqtt_server = "broker.hivemq.com";
const int mqtt_port = 1883;
const char *mqtt_user = "";
const char *mqtt_pass = "";
const char *temp_topic = "data/temp";
const char *cont_topic = "data/contact";
const char *mode_topic = "data/mode";
const char *managemode_topic = "manage/mode";
long int times = 0;  // для времени

WiFiClient wclient;
PubSubClient client(wclient, mqtt_server, mqtt_port);

void refreshData() {
    client.publish(temp_topic, String(tempC));
    client.publish(cont_topic, contactState);
    if (mod == 1)
        client.publish(mode_topic, "Auto Mode");
    else
        client.publish(mode_topic, "Always On");
    delay(1);
}

void callback(const MQTT::Publish &pub) {
    String topic = pub.topic();
    Serial.print(topic);  
    Serial.print(" => ");
    String payload = pub.payload_string();  
    Serial.print(payload);
    Serial.println();
    if (topic == managemode_topic) {
        if (payload[0] == '0') {
            return;
        }
        if (payload[0] == '1') {
            mod = 0;
        } else if (payload[0] == '2')
            mod = 1;
    }
}

void setup() {
    Serial.begin(115200);
    pinMode(ventPin, OUTPUT);
    WiFi.begin(ssid, password);
    while (WiFi.status() != WL_CONNECTED) delay(500);
    WiFi.mode(WIFI_STA);

    Serial.print(WiFi.localIP());

    server.on("/auto", []() {
        server.send(200, "text/html", autoModSwitch());
    });

    server.on("/always", []() {
        server.send(200, "text/html", alwaysModSwitch());
    });

    server.on("/plus", []() {
        server.send(200, "text/html", btPlus());
    });

    server.on("/minus", []() {
        server.send(200, "text/html", btMinus());
    });

    server.on("/xml", XMLcontent);
    server.on("/", WebsiteContent);
    server.begin();
}

String btPlus() {
    if (borderTemp == 100) return String(borderTemp);
    borderTemp += 5;
    return String(borderTemp);
}

String btMinus() {
    if (borderTemp == 0) return String(borderTemp);
    borderTemp -= 5;
    return String(borderTemp);
}

String autoModSwitch() {
    mod = 1;
    return String(mod);
}

String alwaysModSwitch() {
    mod = 0;
    return String(mod);
}

void loop() {
    Serial.println(mod);
    tempRawValue = analogRead(tempIn);
    lightRawValue = analogRead(lightIn);
    tempVoltage = (tempRawValue / 2048.0) * 1000;
    tempC = tempVoltage * 0.1;
    data = "--";

    if (mod == 0) {
        contactState = "NO";
        if (lightRawValue > 3000) {
            contactState = "YES";
            color = "#21e6c1";
        } else {
            contactState = "NO";
            color = "#e9767c";
        }
        data = (String)tempC;
        digitalWrite(ventPin, HIGH);
    }

    if (mod == 1) {
        if (lightRawValue > 3000) {
            contactState = "YES";
            color = "#21e6c1";
            data = (String)tempC;
            if (tempC > borderTemp) {
                Serial.println(tempC);
                Serial.println(borderTemp);
                digitalWrite(ventPin, HIGH);
            } else {
                digitalWrite(ventPin, LOW);
            }
        } else {
            contactState = "NO";
            color = "#e9767c";
            digitalWrite(ventPin, LOW);
        }
    }

    if (WiFi.status() == WL_CONNECTED) {
        if (!client.connected()) {
            if (client.connect(MQTT::Connect(MQTT_client)
                                   .set_auth(mqtt_user, mqtt_pass))) {
                client.set_callback(callback);
                client.subscribe(managemode_topic);
                client.subscribe(temp_topic);
                client.subscribe(cont_topic);
                client.subscribe(mode_topic);
            } else {
                Serial.println("MQTT - error");
            }
        }

        if (client.connected()) {
            client.loop();
            refreshData();
        }
    }

    server.handleClient();
    delay(100);
}