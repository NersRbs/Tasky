rootProject.name = "Tasky"

include(":services:user-service")
include(":services:team-service")

include(":common:user-client")
include(":common:grpc-exception")

include(":proto:user-proto")
include("proto:team-proto")
