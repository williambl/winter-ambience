for colour in white orange magenta light_blue yellow lime pink gray light_gray cyan purple blue brown green red black
do
    rename -e "s/decorative_light_${colour}/${colour}_decorative_light/" *
    sed -i -e "s/decorative_light_${colour}/${colour}_decorative_light/" *
done

