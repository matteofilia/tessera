#!/bin/bash

echo "Script Location: $0"
DIR="$(dirname $0)"

if [ "$(whoami)" = "vampire" ]; then
	echo "Copying files"
	cp -r "../Tessera Website" "/var/www/html"

	echo "Reloading nginx..."
	sudo service nginx reload
else
	echo "ERROR: User is not vampire"
	echo "Please su vampire and try again"
fi
