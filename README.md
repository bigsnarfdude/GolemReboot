## Screenshot of FlowBot in action

![alt text][logo]
[logo]: https://raw.githubusercontent.com/bigsnarfdude/GolemReboot/master/Screen%20Shot%202014-10-25%20at%2012.09.30%20AM.png "FlowBot Screenshot"

#### 1. copy and configure the configuration file in src/main/resources

```
<<<<<<<<<<<<<<<< inside look >>>>>>>>>>>>>>>>>>>>>>>

hipchat-username="231018_1234678"
hipchat-password="yourSecretPassword"
hipchat-nickname="Jane Smith"
hipchat-auth-token="asdfassjKtb0123WcAgqlkjjgsb3qpoiopiI"

powerups = [
  {
    "class": "cc.hypo.golem.bot.powerup.Echo",
    "actorName": "echo"
  }
]

hipchat-rooms=[
  {
    "room":"231018_test",
    "handler":"/user/*"
  }
]

```

#### 2. sbt run

#### 3. @Jane Smith echo "some message to get echo'd back to yourself"
