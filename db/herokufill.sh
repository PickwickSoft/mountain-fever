psql $(heroku pg:credentials:url --app mountain-fever | grep 'postgres://') \
    < db/create_tables.sql