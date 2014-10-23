1. copy and configure the configuration file in main.scala.resources

<<<<<<<<<<<<<<<< inside look >>>>>>>>>>>>>>>>>>>>>>>

hipchat-username="231018_1234678"
hipchat-password="yourSecretPassword"
hipchat-nickname="John Smith"
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

2. sbt run

3. @Jane Smith echo "some message to get echo'd back to yourself"



sbt run
