![alt text][logo]
[logo]: https://raw.github.com/bigsnarfdude/PythonSystemAdminTools/master/Screen%20Shot%202012-12-10%20at%204.09.00%20PM.png "FlowBot Screenshot"

## 1. copy and configure the configuration file in src/main/resources

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

## 2. sbt run

## 3. @Jane Smith echo "some message to get echo'd back to yourself"
