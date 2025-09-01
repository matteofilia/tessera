echo "Running tests..."

echo "Script Location: $0"
DIR="$(dirname $0)"

RED="\033[0;31m"
RESET="\033[0m"
GREEN="\033[0;32m"

cd "Tessera Demo Code"
FILES=$(ls *.tess)

echo "Running tests using the following files: "
echo $FILES

for file in $FILES; do ../run.sh "../Tessera Demo Code/$file" -c
	res=$?	
	echo "Ran ../Tessera Demo Code/$file"
	
	if [ $res -eq 0 ]; then
		echo -e ${GREEN} TEST OK ${RESET}
	else
		echo -e ${RED} TEST FAILURE ${RESET}	
	fi
done















