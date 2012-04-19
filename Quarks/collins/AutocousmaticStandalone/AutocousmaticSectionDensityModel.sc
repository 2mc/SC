//Autocousmatic by Nick Collins (c)2009-2011, released under GNU GPL 3 August 2011
 

AutocousmaticSectionDensityModel {
	
	var densitysource; 
	var transitions; 
	var transitionnow, transitionpos; 
	
	*new {
		^super.new.initAutocousmaticSectionDensityModel; 	
	}
	
	initAutocousmaticSectionDensityModel {
		
		densitysource = [ [ 0.33389018102061, 0.20797362224277, 0.22517071681646, 0.20107268656077, 0.18976968620283, 0.2373120246182, 0.24809447708882, 0.21078352955674, 0.20357439031894, 0.17622670308577, 0.22567150877226, 0.16551627446126, 0.21892763399813, 0.15852171668718, 0.19511553065481, 0.1834139433402, 0.14827324872031, 0.16817103368568, 0.18314895570928, 0.22328772553558, 0.21531962809037, 0.18000604816576, 0.1889017887097, 0.18416060695362, 0.19544030906323, 0.18435501798506, 0.19049287176295, 0.21619181240314, 0.19226881721705, 0.21864542629973, 0.23127211041418, 0.222506531311, 0.22230365010612, 0.21542896057564, 0.21476336199545, 0.24678174966695, 0.23851586684099, 0.24496357058076, 0.25177535665509, 0.23067134252013, 0.26219720696964, 0.28026737729019, 0.20846229343697, 0.22179852190246, 0.30158813277476, 0.2766282523926, 0.28748307951243, 0.32162222483028, 0.3171735917847, 0.32151162988539, 0.32531437193285, 0.29718352003465, 0.33222394555004, 0.30935100512185, 0.32930627187034, 0.36835035841605, 0.33688266015895, 0.36664037912424, 0.37394088825316, 0.35101907194184, 0.36658161006441, 0.36606439312942, 0.40270922176099, 0.38773391227891, 0.37290047906011, 0.42705841722131, 0.35149071071928, 0.33028203961675, 0.30012515400666, 0.35578360374781, 0.39934638023611, 0.33580639149556, 0.39671199586988, 0.37941220035251, 0.35386819636381, 0.37136411805239, 0.50096066058839, 0.89769257715053, 0.36354740377636, 0.39005675635569, 0.41873275856891, 0.29488344118557, 0.31671755035792, 0.41038077836733, 0.33325572943957, 0.39585657062795, 0.53057484877868, 0.26982671223989, 0.32809577435296, 0.33213482912989, 0.264337537565, 0.28526002897983, 0.4586280876304, 0.56271186858118, 0.92079369834541, 0.58491181565306, 0.74023770263975, 0.20733459933559, 0.13068122496189, 0.28757413140061, 0.27988063776392, 0.31589177734288, 0.38200825226806, 0.28837645119415, 0.29266607160351, 0.32634137807457, 0.35723834385093, 0.36496500649342, 0.37674897500992, 0.33131425157038, 0.39161457643067, 0.30267907110734, 0.44088986219779, 0.36161459467616, 0.3396305226262, 0.22873569843669, 0.20100492022306, 0.24384362995569, 0.55743694375959, 0.59852270279339, 0.52050573145081, 0.20686034007771, 0.14470560324244, 0.43540716811226, 0.42291239788486, 0.49085807140877, 0.16201881008843, 0.78486665752949, 0.18301636069345, 0.24682626864828, 0.25333784118526, 0.24821076324256, 0.2703330295454, 0.30611872064005, 0.20601787758364, 0.17103159088035, 0.25325739353409, 0.29094012558314, 0.28961039369499, 0.24096180735888, 0.26184655624496, 0.25277397561861, 0.26347132331865, 0.24841917128783, 0.25616934600475, 0.27065909573068, 0.27884797146291, 0.25077675122549, 0.29208768782986, 0.30517309218469, 0.31343995259107, 0.29347486892656, 0.26703753695698, 0.25844417582846, 0.23945614027788, 0.23178233883579, 0.17486837382934, 0.18551042866264, 0.16755154587422, 0.62843492716853, 0.15080866276746, 0.13563573524486, 0.15635647092762, 0.16492842477082, 0.79305315140976, 0.60508903678118, 0.29828568594906, 0.27245963337377, 0.6845401396389, 0.56768926876082, 0.46855013920224, 0.47908565560198, 0.41006905229053, 0.32963044444467, 0.22495044739006, 0.20866685967354, 0.33777802560261, 0.27327403142457, 0.19999551143868, 0.22387509828779, 0.30853557555109, 0.31967653234197, 0.18944513767488, 0.56301394626868, 0.25773773988504, 0.39734350090112, 0.4864974199741, 0.39932894949063, 0.52064407775432, 0.45642168201298, 0.53277486732809, 0.56369218309653, 0.53546719726424, 0.59517286638342, 0.63806569366633, 0.55847603737731, 0.51668317166695, 0.69347377988157, 0.54116261410632, 0.52522025633036, 0.52519197648203, 0.27075473138051, 0.64753692280417, 1.0, 0.27207797546121, 0.39870815547706, 0.39718970035343, 0.42864490817681, 0.41500661489084, 0.83487364420208, 0.33905374358299, 0.35192231232152, 0.39391289594881, 0.42682650940963, 0.39483839064096, 0.35959214537355, 0.36417853597757, 0.61990352345565, 0.39780943173864, 0.33339358365765, 0.32931662574948, 0.39722302156677, 0.37778076643136, 0.37725445093721, 0.35529580398105, 0.33296459312095, 0.39762513319223, 0.37074741449895, 0.37157451972231, 0.33645876371069, 0.30786342961421, 0.32219320084308, 0.51734159703499, 0.33306877840205, 0.52791966124988, 0.80809364212608, 0.35251830058478, 0.39926162103158, 0.4274752637926, 0.50932544505368, 0.71879725930008, 0.38948876423845, 0.35901849301856, 0.29641247614593, 0.3716562473291, 0.35610107919009, 0.60270897898603, 0.30379377390232, 0.75386739949454, 0.22359416391535, 0.39379429582847, 0.38139859608094, 0.39003219978639, 0.29266784223233, 0.31252956881309, 0.28807423196932, 0.63836346560632, 0.75158931008542, 0.40739085223599, 0.33646230622366, 0.38165952437975, 0.34604848187306, 0.33593459230305, 0.34378003457664, 0.33260913922823, 0.29795717749955, 0.31556658136894, 0.35523914009282, 0.25531952475635, 0.30011484845735, 0.31442598195059, 0.43209738707963, 0.51669257652418, 0.34735744417843, 0.40846893107077, 0.34271590360207, 0.37927405741083, 0.38206507809256, 0.28684947119258, 0.34712518864311, 0.40821227839179, 0.37744275897562, 0.44441758937338, 0.47881521950971, 0.55055227694597, 0.40599070850911, 0.49701923706699, 0.39937710293711, 0.39247449760109, 0.27414373691191, 0.35517064732449, 0.29306550312082, 0.31132650216228, 0.28847996079927, 0.31170212653632, 0.26520935665812, 0.26041479728458, 0.3104629286384, 0.24000504005712, 0.23625985694103, 0.27036420416159, 0.2389303180264, 0.3917050266283, 0.29669659271527, 0.28585841051061, 0.36684383071513, 0.34581310623995, 0.32074277511779, 0.28888254348354, 0.30744891244605, 0.25935573482131, 0.26361602609175, 0.26082786508776, 0.30635658615128, 0.23976535578339, 0.25873186907899, 0.26583872571828, 0.24596657582841, 0.24723758501531, 0.41017053235709, 0.39315911773099, 0.36968789682527, 0.29383464708276, 0.31154385830485, 0.30248548727292, 0.36181528942748, 0.27234521488117, 0.36586872012873, 0.30042908894006, 0.26472078989085, 0.28514533384609, 0.23050827676956, 0.28023452682183, 0.22382677203867, 0.23612014104421, 0.22242858194272, 0.2725477083491, 0.28114863576184, 0.22610810911747, 0.28333419616346, 0.2152034818852, 0.28262915338984, 0.25804706786919, 0.19776421106091, 0.27892209936259, 0.23950660259123, 0.20055740579992, 0.20805475369027, 0.19193460125913, 0.18330636388965, 0.21125458403176, 0.20974664482161, 0.25888098479216, 0.24134183531692, 0.18165186950525, 0.20998992666586, 0.21062957124083, 0.2349358601391, 0.19658172961775, 0.21908960238329, 0.10372950001727, 0.014730675241416, 0.0078048792958529], [ 0.64232831177905, 0.24074118464324, 0.097205896954873, 0.087614233032294, 0.77016463895805, 0.17956548366556, 0.13605231956444, 0.11971777798931, 0.10487080332502, 0.42481158427509, 0.11924171515321, 0.099230744642092, 0.095314899232603, 0.11856061165454, 1.0, 0.6461318503936, 0.26035000988797, 0.13671474627573, 0.11174383631962, 0.80561946567599, 0.78012571182549, 0.65721491836067, 0.18360488822829, 0.14781150276771, 0.14029951655677, 0.13090011550971, 0.11793200437691, 0.1087390592539, 0.10719447947174, 0.86494560586652, 0.56487671328432, 0.21195067448988, 0.81173775349732, 0.31428993029621, 0.18556442504191, 0.15261333086383, 0.53819419212481, 0.57406423188198, 0.13287018315315, 0.10568198660946, 0.37120208641134, 0.46881518947715, 0.56626059174171, 0.2220972378024, 0.17682929482225, 0.37968710720271, 0.18203015956853, 0.14911805709759, 0.12916017335788, 0.70649214293543, 0.26239991449605, 0.17595212930715, 0.65818690685057, 0.34920309854307, 0.848447409391, 0.50653165396965, 0.22121520644014, 0.21622421021085, 0.29001273369119, 0.55758318574189, 0.25688719489629, 0.14694331365293, 0.73266841176546, 0.28613781895895, 0.65823396705132, 0.38638399575272, 0.16077240454691, 0.76775951640725, 0.74321569490726, 0.207924215922, 0.12630515917252, 0.12502279631087, 0.15118809561898, 0.17146382273372, 0.77438560155332, 0.17537448421132, 0.75369569084126, 0.18316978226506, 0.11732461541671, 0.14639360694409, 0.17549543614169, 0.21434401589679, 0.58486928949326, 0.28175144197783, 0.72948534857899, 0.24682101906396, 0.13117438879721, 0.14996433103161, 0.24262548784558, 0.18819257437492, 0.72966056347363, 0.48875394835084, 0.16079783786614, 0.16806623037776, 0.1983046547138, 0.21042968769146, 0.21153742266233, 0.57105492738771, 0.21529293434417, 0.15873057925407, 0.2489191731418, 0.21113418000672, 0.21776462821056, 0.58687108836499, 0.25873146862527, 0.60616467852413, 0.60301801595898, 0.19501597245412, 0.17743102386375, 0.78758587243539, 0.16943894202666, 0.70485020365169, 0.24452173978827, 0.1958627497325, 0.22445556320121, 0.23721187547473, 0.21606202344443, 0.22533707179568, 0.19928198131774, 0.16383630275875, 0.26134226284438, 0.16512964416033, 0.1847936927193, 0.24125840399733, 0.17408947102239, 0.20096772709992, 0.17519851957221, 0.29588284499318, 0.19689144742917, 0.17432309162184, 0.21998103436172, 0.17957170943045, 0.14667059313008, 0.18976507405713, 0.17193140897602, 0.16502532118498, 0.18479029916827, 0.14382879437031, 0.1259310688684, 0.13434957148113, 0.11737780579132, 0.11737905555043, 0.12042897277598, 0.1046068311159, 0.10794916706841, 0.13158106967774, 0.11428612125022, 0.11807980830009, 0.12955990397952, 0.13035613288625, 0.13006571456466, 0.13148256845989, 0.13025851959672, 0.10786908051267, 0.10966790452087, 0.097951066896484, 0.10588296689464, 0.09423327010862, 0.088159525627494, 0.082440207237386, 0.071953706638831, 0.060358561567205, 0.057849493358564, 0.05215587477457, 0.06139888095353, 0.055431356791881, 0.04829842485634, 0.044499594423159, 0.043245000626064, 0.04133039061287, 0.040973876512947, 0.037848812445185], [ 0.60624866822845, 0.26201110685224, 0.66984841995901, 0.77859256021577, 0.71713015616261, 0.69000911843303, 0.48340271589826, 0.60904951513719, 0.60951548887947, 0.48076236376642, 0.35398784955806, 0.91195379372475, 0.75269318055217, 0.70269877961261, 0.65519408977756, 0.66259201932988, 0.79587474766461, 0.58277081839924, 0.71011510239039, 0.72690411673838, 0.78615352044708, 0.71360364779393, 0.82543992904973, 0.59140121876637, 0.66994776145444, 0.57965183508838, 0.66452416952418, 0.61839389625022, 0.33953892084041, 0.51610217560118, 0.7218140632023, 0.55926566673774, 0.6491672156164, 0.55403802774399, 0.6085495923057, 0.54100979084618, 0.43034103060165, 0.47476835450329, 0.62190412612214, 0.64183402802002, 0.42100267610289, 0.67654591350206, 0.48226594344511, 0.32298241773086, 0.38223312685151, 0.43791839564952, 0.5494173489173, 0.54676522487625, 0.47301929840604, 0.63640325906307, 0.81247265563145, 0.52075388333859, 0.49493824209786, 0.50960169131171, 0.77432497486081, 0.56018954651214, 0.59295522034441, 0.58014737281845, 0.48606335621361, 0.64367392222817, 0.54196090929298, 0.4253687367604, 0.58568033448904, 0.6435061953208, 0.41248084502106, 0.57300683966518, 0.52057206017335, 0.57168643174443, 0.5215786433217, 0.5933181448475, 0.49801258231771, 0.54925688282015, 0.53420161538372, 0.44656575767149, 0.59318310640936, 0.53738920001025, 0.44239247648792, 0.54264278354298, 0.5861870057622, 0.51263886257656, 0.54232410705929, 0.44532438985064, 0.60538180895143, 0.50014262278171, 0.54601336172631, 0.51634830778704, 0.57889523945758, 0.61449189971483, 0.4110406201974, 0.94982411747423, 0.50693314183046, 0.56093088387187, 0.71458997337199, 0.48085962259646, 1.0, 0.61395188517186, 0.61012765909354, 0.63079749594364, 0.61227101211709, 0.51703579166708, 0.6564837135322, 0.68484338387239, 0.78492833058276, 0.55693833280062, 0.53026033468125, 0.63182446040412, 0.51042421362989, 0.58856472582954, 0.51958550773525, 0.6305290895822, 0.5188288057638, 0.72549929110363, 0.68913664823697, 0.65117243536685, 0.55005878149843, 0.4901025811599, 0.33350914212959, 0.5010767318815, 0.49138742876956, 0.36460404977961, 0.0087733711727629], [ 0.12862134965816, 0.12194606032336, 0.16157021174281, 0.123208677634, 0.095017042794975, 0.10278351002026, 0.097896087802978, 0.13467962211133, 0.098431629964952, 0.12211661522759, 0.095186195835409, 0.12815230282865, 0.16988658459455, 0.21588142166076, 0.1885261717177, 0.1417903383581, 0.13852968449185, 0.12262533066805, 0.12422343271827, 0.15367866387973, 0.16830119906759, 0.23081719933768, 0.21075854596444, 0.22129212033434, 0.19039007451121, 0.2370552936248, 0.20257683868444, 0.28777598320753, 0.27437954159621, 0.34410923311455, 0.36608212158706, 0.35411838613873, 0.38046586985992, 0.36775390612516, 0.36867836617511, 0.4304124910791, 0.30680656925992, 0.35122461541149, 0.5192267992729, 0.1296789898592, 0.27535487688598, 0.23494217296932, 0.31178715575739, 0.23762509022952, 0.37008084799883, 0.10922040405096, 1.0, 0.55612699602167, 0.20106368953103, 0.2627006647731, 0.13610913463539, 0.22919252803472, 0.46237672903531, 0.28470068350961, 0.44853187641527, 0.16725861782431, 0.41755523361417, 0.36580808579782, 0.51117246055547, 0.1269928024796, 0.38937252495733, 0.61211270681673, 0.26799101384428, 0.33013426765823, 0.55426986460793, 0.28475066607559, 0.3236953729294, 0.22724223241032, 0.18878201019249, 0.39591043108353, 0.43520294823822, 0.32855985749558, 0.34730120239107, 0.32281036901375, 0.38293056680722, 0.64206862392746, 0.51547422093617, 0.13252380672137, 0.25640400761493, 0.19814515198711, 0.35168239486036, 0.22508543245794, 0.25572352555697, 0.35294904453028, 0.25299405204029, 0.33289532916865, 0.45568052842645, 0.46449696463009, 0.40995537119284, 0.28373163871513, 0.42918572435085, 0.43879317612449, 0.25385361067252, 0.37466557798355, 0.26369535311308, 0.2931874674514, 0.42844351809011, 0.60914419820207, 0.58234152844605, 0.38766255197572, 0.65049085303938, 0.18730989730621, 0.054372850146718, 0.75524250462096, 0.61335542153025, 0.47520029968161, 0.30153673209571, 0.41634532274842, 0.20606828308417, 0.10735223867121, 0.36781120774832, 0.24592180105721, 0.40043816652576, 0.32345381949184, 0.47350175402524, 0.23410934597175, 0.1225907793932, 0.3698970434793, 0.1698789667899, 0.27804568426911, 0.12364232864754, 0.10188924720584, 0.27098463944801, 0.29185065314792, 0.42911377053786, 0.1813412774437, 0.56627863818319, 0.18686454387844, 0.50418167127541, 0.16625763117102, 0.20850672824317, 0.28092324182085, 0.39240169349112, 0.28489891958442, 0.12828199404896, 0.11657410038184, 0.12792797146371, 0.16983367920774, 0.16831505603051, 0.13331300177812, 0.130039368543, 0.14995692407451, 0.17110115041694, 0.19766936708933, 0.1457320938359, 0.18291535669314, 0.20923919630002, 0.22512902600401, 0.22120961629575, 0.15974744740669, 0.1502235422163, 0.12619643024853, 0.13156245725252, 0.12930957405791, 0.17574191277351, 0.1488415247735, 0.10869185619, 0.16096122314722, 0.18409765338212, 0.20896608606349, 0.13942102154646, 0.14835904815925, 0.1907638944132, 0.10396633493433, 0.22743191784522, 0.1609997068826, 0.13327230023279, 0.13551011485935, 0.069052633884918, 0.27911762307688, 0.098510525021598, 0.20654776922038, 0.19150911317004, 0.26136125402726, 0.23709946725977, 0.11594071949211, 0.20014623218722, 0.14659462182616, 0.11490971970925, 0.091811607641105, 0.080489541154927, 0.10293491751018, 0.12455695109095, 0.14428094633169, 0.10320545999225, 0.066077101860308, 0.06126215013179, 0.15539491963135, 0.20882471678562, 0.09159948648328, 0.12418997036596, 0.14396227635601, 0.20205295532859, 0.16470114252961, 0.17596480936488, 0.18853397954686, 0.090812616798738, 0.15391477617234, 0.20469666919539, 0.1201349440494, 0.087500612487743, 0.092182227001612, 0.18163442276625, 0.073704893677931, 0.097744888804891, 0.094649696288621, 0.086082915288724, 0.064673811564646, 0.046354432609822, 0.068601202520085, 0.10750354132254, 0.0921022393085, 0.14928319356933, 0.092372498747656, 0.11797644715936, 0.091254945010704, 0.099751326891983, 0.10316615513839, 0.11870928037181, 0.097290210918553, 0.11877104007452, 0.14351517006399, 0.093763020891389, 0.19247574371562, 0.18847487483101, 0.18516325574064, 0.14802648315037, 0.13795823442576, 0.072908961638071], [ 0.065529597747462, 0.08460749082307, 0.094136062706336, 0.14160601567306, 0.16860422649999, 0.18501557092864, 0.19355495109013, 0.2356596933867, 0.21275349909075, 0.2434279353823, 0.43209938924053, 0.52220357726772, 0.60241421504987, 0.63789484230874, 0.67932465380339, 0.55966916386206, 0.5119682099256, 0.33395625067856, 0.29753097888909, 0.21843530268434, 0.15905861257276, 0.11219897926645, 0.10941362335124, 0.096654214043757, 0.077784776282881, 0.06942647465077, 0.060754102028278, 0.049549902410479, 0.052588374498876, 0.047793212829765, 0.051238879080823, 0.050536592968862, 0.059493135768045, 0.093377623668187, 0.16554530178294, 0.16711787042977, 0.1769743895413, 0.17105453887119, 0.16834398189395, 0.19942541010025, 0.21414403863408, 0.36711225529491, 0.36358321926591, 0.45468059038541, 0.53251134292942, 0.88260867740541, 0.73021601082504, 0.29171170672042, 0.24428984324124, 0.15716076214052, 0.12771289884885, 0.11827373762409, 0.085826394702553, 0.090947148105014, 0.089112135658815, 0.096916364230772, 0.13245390029146, 0.13537751701768, 0.25359402856076, 0.32709139976609, 0.43789900110706, 1.0, 0.89973721137317, 0.46711193843897, 0.39705701332054, 0.21369156191505, 0.46036748625183, 0.25147735801679, 0.21026890513395, 0.23295746302734, 0.22270708524579, 0.20033823947121, 0.2143541324149, 0.15291415671833, 0.12850460059653, 0.08115226156218, 0.076899804533947, 0.091572085681456, 0.071446146028304, 0.07633303441575, 0.094419907568735, 0.1677675084948, 0.14982356003058, 0.17544202173928, 0.18493968344189, 0.23134745135023, 0.20006221338076, 0.2421118111688, 0.33545372248659, 0.4224875303011, 0.49867021880047, 0.57320323961646, 0.46347460421342, 0.40465191638586, 0.35612080056053, 0.2950106744978, 0.26515101545222, 0.19341262728275, 0.17064892036347, 0.14395070136836, 0.13629686707717, 0.13283257208362, 0.15730799289849, 0.1679002807553, 0.22276640103775, 0.26055190911892, 0.33020306994529, 0.39082889809837, 0.49554542949131, 0.54007662186054, 0.88432421263213, 0.56755576551471, 0.3990890241, 0.27539844420511, 0.21815423683384, 0.22683598037055, 0.22577349033158, 0.22664390884157, 0.19534571854766, 0.23784443313279, 0.25116230300785, 0.41151268842873, 0.35103471491747, 0.44316117273813, 0.49717140298535, 0.71666719097693, 0.6396970678591, 0.35294544518352, 0.25687606585467, 0.23461116764677, 0.23907078637053, 0.31784429780844, 0.27784782623224, 0.34486839516635, 0.27298638349839, 0.43378342313472, 0.43629517520794, 0.41917480790924, 0.3604127043603, 0.43008343727335, 0.49553406020955, 0.36440825852717, 0.25282497653223, 0.24325285380101, 0.25443984307512, 0.2567820250424, 0.33050457336026, 0.31263036015833, 0.42745184682206, 0.53821770810608, 0.54714699270303, 0.5625704150961, 0.50823235214297, 0.46532307085838, 0.48411124940316, 0.69207019990142, 0.51080884093562, 0.58771173064492, 0.36043978575676, 0.2555580239587, 0.49583706250431, 0.62564990126874, 0.68064150140032, 0.67392419551755, 0.65742708578642, 0.66389401930085, 0.54909671037049, 0.63781375619081, 0.718894346682, 0.6513479726901, 0.67615427701038, 0.91204689612986, 0.46826605721797, 0.44001837927838, 0.343877934708, 0.38912035926558, 0.41382525955661, 0.38556570839924, 0.3687549869274, 0.27177578236802, 0.25725966285977, 0.20955073429988, 0.19940122882244, 0.35590027909177, 0.18478869440708, 0.34481381447039, 0.23848130211153, 0.24334840245206, 0.25305014164946, 0.26153406766116, 0.28593830805694, 0.342706044379, 0.30892049008372, 0.35311003880462, 0.40290512353119, 0.44693077943992, 0.54683986175815, 0.55809075894286, 0.30998233990669, 0.2186029390974, 0.31623697937681, 0.31219219438594, 0.26109085941312, 0.25425024607725, 0.25385050642603, 0.23898492720687, 0.2024082713575, 0.22203387136537, 0.28731771569223, 0.3822812540059, 0.43115225368526, 0.24653309534387, 0.32438532171327, 0.389433812535, 0.37845393856625, 0.42126943017358, 0.39758042704798, 0.41050037579313, 0.48246737891657, 0.5560195741684, 0.55301802087364, 0.43801171110952, 0.58557665621936, 0.67210055968358, 0.59396246294486, 0.36431859503433, 0.26969948656141, 0.23248694727971, 0.23769189759875, 0.26349428200204, 0.28942642684016, 0.33011705555699, 0.27470183196325, 0.41858458247482, 0.30542902681002, 0.30401038772722, 0.25472742752331, 0.2794944265833, 0.38500525857304, 0.29699415101387, 0.29624107554231, 0.39140952975583, 0.3510958767033, 0.36856783900395, 0.3856166703121, 0.39152828579102, 0.42658736983571, 0.43475499905385, 0.4094686986729, 0.35952692291557, 0.3547200556797, 0.40600555917587, 0.60525973983656, 0.61536651112963, 0.60633010474218, 0.42300103484175, 0.31403466844393, 0.27432688203494, 0.26826743690443, 0.24907126030863, 0.28793779717005, 0.29188278925084, 0.26304246270894, 0.31486880236091, 0.25576572061976, 0.32472307640096, 0.38324562001469, 0.74151598319468, 0.60570516330058, 0.44389706647042, 0.39819025760614, 0.35035045776722, 0.2355120772515, 0.16936631816215, 0.24359627765544, 0.20693365967335, 0.23264927954457, 0.17185995606952, 0.16235592552413, 0.1671720282741, 0.19233787274587, 0.17283535965662, 0.20636677792803, 0.20000509284593, 0.37630385909082, 0.26410222214414, 0.24435765925499, 0.23724704794339, 0.25810968271505, 0.17398493898905, 0.15459948137895, 0.17003687158217, 0.16030724391682, 0.24066278419251, 0.27708026503248, 0.24606092591872, 0.36476487363019, 0.56498186344258, 0.62267638499627, 0.4080023736029, 0.31781667469076, 0.32000160946049, 0.32148454655949, 0.27665425851589, 0.39519584864741, 0.22901489957897, 0.21740677463945, 0.23115111779002, 0.3231170280387, 0.27812153473014, 0.30109689707712, 0.28706615692176, 0.27549447467167, 0.38447671751984, 0.36910715897007, 0.43996575081804, 0.45089815883844, 0.74047378006251, 0.6009296891063, 0.2166638410719, 0.29000588569016, 0.55765541686488, 0.29359394680827, 0.19681265652351, 0.23303404927383, 0.20419917796264, 0.20376701858432, 0.20293159525553, 0.22865804143777, 0.22017359571706, 0.22407966560425, 0.24131443237726, 0.30201135822583, 0.22644403248518, 0.26460085031578, 0.36213207841729, 0.447936371307, 0.50907939755454, 0.49738288498095, 0.42285958729128, 0.38960014047705, 0.30987027818373, 0.25710239830235, 0.19481139996911, 0.16704708408893, 0.19314932229476, 0.18617818362981, 0.273419374297, 0.26567764525376, 0.40799986868368, 0.25767157096318, 0.43265620535615, 0.47625610849892, 0.51552795319804, 0.65647131707808, 0.40751050858943, 0.2174231864885, 0.081800873535733, 0.060275512650155, 0.054356612163221, 0.055453300014341, 0.054362727012741, 0.079457654607795, 0.17228117982961, 0.22702929323016, 0.17233168132066, 0.1438603291996, 0.12480788303308, 0.14894882265589, 0.14472163176351, 0.15995680307964, 0.15342512786023, 0.15606235778675, 0.15712594614568, 0.16931902329681, 0.17040440047863, 0.14905910481365, 0.13674158240465, 0.15245629560017, 0.18173170904497, 0.1823589223387, 0.22341109197156, 0.23120276276876, 0.29008532312029, 0.33728970641908, 0.41727196815371, 0.55391483879711, 0.56482826598267, 0.4970343067978, 0.29779081328596, 0.15669493091521, 0.085479359648528, 0.059007260525988, 0.059291118520745, 0.055138719884052, 0.059918766973261, 0.27646491137019, 0.49894541238356, 0.54725565415735, 0.30316368084144, 0.32292858089062, 0.28102971465129, 0.27661239209174, 0.34226528026565, 0.4133918809765, 0.47801008627372, 0.297969104144, 0.30396654735097, 0.30782261966438, 0.28884154171477, 0.30673016020974, 0.31281392188594, 0.32288603528241, 0.33242023449566, 0.36251910921125, 0.40195380747945, 0.30976924414295, 0.3644618093597, 0.37421222604774, 0.32186414579656, 0.29314720132855, 0.26972141354271, 0.22456413894522, 0.22724317641893, 0.19820971391482, 0.24346339823038, 0.23211118086692, 0.16086940499799, 0.1373575531079, 0.1705156064929, 0.28986826010989, 0.49492472052585, 0.26853850693539, 0.22921649747796, 0.29691624081909, 0.2416841249932, 0.1823938022567, 0.17003507791364, 0.16771358967198, 0.21762512017268, 0.20116206603647, 0.19024509954088, 0.30770800020954, 0.25058125664609, 0.3085714375363, 0.26170126971096, 0.24216993434438, 0.21890805738033, 0.23432237238933, 0.35179159010127, 0.29382024386981, 0.1905323020282, 0.2070757986406, 0.19481299869314, 0.22166365177868, 0.24074347139996, 0.28494212870446, 0.32263027079199, 0.3716986992694, 0.37163683922363, 0.55827737174251, 0.33931718577278, 0.33623062849412, 0.28324328650836, 0.55885119719823, 0.39225764928017, 0.55401815194552, 0.32346083795551, 0.28596146792218, 0.40350259679444, 0.68405142599995, 0.65268141325682, 0.37905766911284, 0.57468617471624, 0.52173521451876, 0.39004354446512, 0.60089835417529, 0.58554393089941, 0.51496201585378, 0.69009521639331, 0.49050288653273, 0.68189088101389, 0.45416529987738, 0.22842725280156, 0.24239443670862, 0.64130923662495, 0.75892001889155, 0.41355115514707, 0.3731447514771, 0.34096910457477, 0.47740857045997, 0.33301569074866, 0.3399110984968, 0.27098799995241, 0.30619354884975, 0.27112946316341, 0.35292580274105, 0.30255774442521, 0.41027839124306, 0.40886380171635, 0.3450618080368, 0.28695292499031, 0.4973704466511, 0.34602444119074, 0.27715958514188, 0.30340867758123, 0.3139302433309, 0.33096910001122, 0.40732763052747, 0.44963735036643, 0.29876897669247, 0.28851836791803, 0.27465585382819, 0.33073537985616, 0.35628956081689, 0.29045660817938, 0.30810998624787, 0.33051244661088, 0.32086975660982, 0.33498163466283, 0.38212062489918, 0.38405833353535, 0.36048170894201, 0.39765035553461, 0.4813040861296, 0.32780441722209, 0.38322961941295, 0.35924943929581, 0.38499972214079, 0.52133171703889, 0.44792853970674, 0.2894394464091, 0.54406248029038, 0.54957821837251, 0.30215578557624, 0.41342855275124, 0.36783270444108, 0.36081300852382, 0.23731827784546, 0.42866843554136, 0.7971975968893, 0.65806297120963, 0.57501041682803, 0.24208633491053, 0.51103571956944, 0.60494320883887, 0.55858065992533, 0.22485725381134, 0.3823138255194, 0.70886152190233, 0.54120177917921, 0.51941972433381, 0.26808013903968, 0.63638945546646, 0.52359171535132, 0.60484238005106, 0.44415690499901, 0.48020773033388, 0.34171720666028, 0.47214825143685, 0.7839646256394, 0.59257057699537, 0.31014403392979, 0.39003831451287, 0.66470087138319, 0.60672774318278, 0.33186713865672, 0.34771282944669, 0.4568133619793, 0.72929213576228, 0.50567338395691, 0.36506550472005, 0.34406142336422, 0.44512918177705, 0.43316520832603, 0.33358462671883, 0.41164679118834, 0.35905635916174, 0.4056954064808, 0.55146124748476, 0.30755691669864, 0.29360015844282, 0.36920536205263, 0.4659500786909, 0.35124875377862, 0.33623935274486, 0.35230075840641, 0.38842526795738, 0.45242626025365, 0.47188333316387, 0.48616967970489, 0.47708673260681, 0.41562681097093, 0.55356064148076, 0.60826338511221, 0.72690622834874, 0.46223047907582, 0.334605239238, 0.39629963946532, 0.61326050478932, 0.64833068368989, 0.30977998527066, 0.4098117931365, 0.69920121473249, 0.73327326284465, 0.52517500902303, 0.3322101647471, 0.47063962546283, 0.5564800608012, 0.56728053514603, 0.52590552246193, 0.43041844207107, 0.34730663398986, 0.42492263605135, 0.55585099164245, 0.60622840376253, 0.44617082846043, 0.40143985831288, 0.40312057024111, 0.46922925608374, 0.44372418749338, 0.47584082576683, 0.37074048511856, 0.51595652352746, 0.52022642074377, 0.55500647963894, 0.40924951569256, 0.59987820853998, 0.42769156202981, 0.63658199261313, 0.41351610700327, 0.46778915541242, 0.70497612267168, 0.67421470577016, 0.29265487769465, 0.23834560943382, 0.26610907516353, 0.28125073891068, 0.30109879073567, 0.28804895418058, 0.31461661205438, 0.37812899432938, 0.4300753699987, 0.46290920639124, 0.46040445057869, 0.41093547529942, 0.3923985138589, 0.36283421889877, 0.31602262740492, 0.24983526403462, 0.22309912053027, 0.2117053558344, 0.19737878652984, 0.21050310767098, 0.3072250032072, 0.23139542001008, 0.23871236764037, 0.27243630672735, 0.2933728299518, 0.47632798622234, 0.4322451702276, 0.44396258988182, 0.51601601276817, 0.61792881454313, 0.34470914538622, 0.36805767155668, 0.31459847468928, 0.46204583457217, 0.43046711981162, 0.22395961334326, 0.2159520315596, 0.20795309623421, 0.28447678367393, 0.24824821893177, 0.25356193546411, 0.33251207313684, 0.41412679575875, 0.46488367383475, 0.38438826595247, 0.4052724975347, 0.34164203502658, 0.36563373156788, 0.28239723767325, 0.254411621725, 0.28928561402449, 0.3977654086863, 0.18403700543165, 0.24708099418432, 0.2934519923896, 0.32970355769272, 0.29004367712651, 0.196320964232, 0.20221543679186, 0.3683831994317, 0.31477634917702, 0.31849314505654, 0.37480496343196, 0.65348827600165, 0.48089545465991, 0.27435332241572, 0.28959118692942, 0.26545823103063, 0.24680109031219, 0.25696551541461, 0.27423756900487, 0.28925792828133, 0.28204723561427, 0.26187840372196, 0.24358643965432, 0.28017781743941, 0.25187740804648, 0.27371392070261, 0.29052380250322, 0.29283654709652, 0.29851560143323, 0.28787207990121, 0.37484712065891, 0.37198808516224, 0.3459460212289, 0.32532525382232, 0.28379965466604, 0.26745696803822, 0.26283230929313, 0.26580398481966, 0.25707869744893, 0.20993065049201, 0.20858424270669, 0.20146589797722, 0.23825060468231, 0.22915199112667, 0.23158031075898, 0.23281551643893, 0.24754076909579, 0.2677578775399, 0.22509213146944, 0.21865790331923, 0.21516132295222, 0.2297773578455, 0.17914390599485, 0.1635612000932, 0.14913461911944, 0.14660578901686, 0.15718426506753, 0.15317684382839, 0.15714182961931, 0.16012646375219, 0.14531408380529, 0.12668050465417, 0.11808582390152, 0.11981737506824, 0.12727491429873, 0.13608386240276, 0.13780246501284, 0.12464535969485, 0.11336058320557, 0.093280967646554, 0.089919412908016, 0.08892047923397, 0.094664219643272, 0.087599264280386, 0.080769116446813, 0.062450782737364, 0.053033137288245, 0.048012718133898, 0.04243818104081, 0.039703785417218, 0.0384223228426] ]; 
		
		
//		densitysource = [ 0.33389018102061, 0.20797362224277, 0.22517071681646, 0.20107268656077, 0.18976968620283, 0.2373120246182, 0.24809447708882, 0.21078352955674, 0.20357439031894, 0.17622670308577, 0.22567150877226, 0.16551627446126, 0.21892763399813, 0.15852171668718, 0.19511553065481, 0.1834139433402, 0.14827324872031, 0.16817103368568, 0.18314895570928, 0.22328772553558, 0.21531962809037, 0.18000604816576, 0.1889017887097, 0.18416060695362, 0.19544030906323, 0.18435501798506, 0.19049287176295, 0.21619181240314, 0.19226881721705, 0.21864542629973, 0.23127211041418, 0.222506531311, 0.22230365010612, 0.21542896057564, 0.21476336199545, 0.24678174966695, 0.23851586684099, 0.24496357058076, 0.25177535665509, 0.23067134252013, 0.26219720696964, 0.28026737729019, 0.20846229343697, 0.22179852190246, 0.30158813277476, 0.2766282523926, 0.28748307951243, 0.32162222483028, 0.3171735917847, 0.32151162988539, 0.32531437193285, 0.29718352003465, 0.33222394555004, 0.30935100512185, 0.32930627187034, 0.36835035841605, 0.33688266015895, 0.36664037912424, 0.37394088825316, 0.35101907194184, 0.36658161006441, 0.36606439312942, 0.40270922176099, 0.38773391227891, 0.37290047906011, 0.42705841722131, 0.35149071071928, 0.33028203961675, 0.30012515400666, 0.35578360374781, 0.39934638023611, 0.33580639149556, 0.39671199586988, 0.37941220035251, 0.35386819636381, 0.37136411805239, 0.50096066058839, 0.89769257715053, 0.36354740377636, 0.39005675635569, 0.41873275856891, 0.29488344118557, 0.31671755035792, 0.41038077836733, 0.33325572943957, 0.39585657062795, 0.53057484877868, 0.26982671223989, 0.32809577435296, 0.33213482912989, 0.264337537565, 0.28526002897983, 0.4586280876304, 0.56271186858118, 0.92079369834541, 0.58491181565306, 0.74023770263975, 0.20733459933559, 0.13068122496189, 0.28757413140061, 0.27988063776392, 0.31589177734288, 0.38200825226806, 0.28837645119415, 0.29266607160351, 0.32634137807457, 0.35723834385093, 0.36496500649342, 0.37674897500992, 0.33131425157038, 0.39161457643067, 0.30267907110734, 0.44088986219779, 0.36161459467616, 0.3396305226262, 0.22873569843669, 0.20100492022306, 0.24384362995569, 0.55743694375959, 0.59852270279339, 0.52050573145081, 0.20686034007771, 0.14470560324244, 0.43540716811226, 0.42291239788486, 0.49085807140877, 0.16201881008843, 0.78486665752949, 0.18301636069345, 0.24682626864828, 0.25333784118526, 0.24821076324256, 0.2703330295454, 0.30611872064005, 0.20601787758364, 0.17103159088035, 0.25325739353409, 0.29094012558314, 0.28961039369499, 0.24096180735888, 0.26184655624496, 0.25277397561861, 0.26347132331865, 0.24841917128783, 0.25616934600475, 0.27065909573068, 0.27884797146291, 0.25077675122549, 0.29208768782986, 0.30517309218469, 0.31343995259107, 0.29347486892656, 0.26703753695698, 0.25844417582846, 0.23945614027788, 0.23178233883579, 0.17486837382934, 0.18551042866264, 0.16755154587422, 0.62843492716853, 0.15080866276746, 0.13563573524486, 0.15635647092762, 0.16492842477082, 0.79305315140976, 0.60508903678118, 0.29828568594906, 0.27245963337377, 0.6845401396389, 0.56768926876082, 0.46855013920224, 0.47908565560198, 0.41006905229053, 0.32963044444467, 0.22495044739006, 0.20866685967354, 0.33777802560261, 0.27327403142457, 0.19999551143868, 0.22387509828779, 0.30853557555109, 0.31967653234197, 0.18944513767488, 0.56301394626868, 0.25773773988504, 0.39734350090112, 0.4864974199741, 0.39932894949063, 0.52064407775432, 0.45642168201298, 0.53277486732809, 0.56369218309653, 0.53546719726424, 0.59517286638342, 0.63806569366633, 0.55847603737731, 0.51668317166695, 0.69347377988157, 0.54116261410632, 0.52522025633036, 0.52519197648203, 0.27075473138051, 0.64753692280417, 1.0, 0.27207797546121, 0.39870815547706, 0.39718970035343, 0.42864490817681, 0.41500661489084, 0.83487364420208, 0.33905374358299, 0.35192231232152, 0.39391289594881, 0.42682650940963, 0.39483839064096, 0.35959214537355, 0.36417853597757, 0.61990352345565, 0.39780943173864, 0.33339358365765, 0.32931662574948, 0.39722302156677, 0.37778076643136, 0.37725445093721, 0.35529580398105, 0.33296459312095, 0.39762513319223, 0.37074741449895, 0.37157451972231, 0.33645876371069, 0.30786342961421, 0.32219320084308, 0.51734159703499, 0.33306877840205, 0.52791966124988, 0.80809364212608, 0.35251830058478, 0.39926162103158, 0.4274752637926, 0.50932544505368, 0.71879725930008, 0.38948876423845, 0.35901849301856, 0.29641247614593, 0.3716562473291, 0.35610107919009, 0.60270897898603, 0.30379377390232, 0.75386739949454, 0.22359416391535, 0.39379429582847, 0.38139859608094, 0.39003219978639, 0.29266784223233, 0.31252956881309, 0.28807423196932, 0.63836346560632, 0.75158931008542, 0.40739085223599, 0.33646230622366, 0.38165952437975, 0.34604848187306, 0.33593459230305, 0.34378003457664, 0.33260913922823, 0.29795717749955, 0.31556658136894, 0.35523914009282, 0.25531952475635, 0.30011484845735, 0.31442598195059, 0.43209738707963, 0.51669257652418, 0.34735744417843, 0.40846893107077, 0.34271590360207, 0.37927405741083, 0.38206507809256, 0.28684947119258, 0.34712518864311, 0.40821227839179, 0.37744275897562, 0.44441758937338, 0.47881521950971, 0.55055227694597, 0.40599070850911, 0.49701923706699, 0.39937710293711, 0.39247449760109, 0.27414373691191, 0.35517064732449, 0.29306550312082, 0.31132650216228, 0.28847996079927, 0.31170212653632, 0.26520935665812, 0.26041479728458, 0.3104629286384, 0.24000504005712, 0.23625985694103, 0.27036420416159, 0.2389303180264, 0.3917050266283, 0.29669659271527, 0.28585841051061, 0.36684383071513, 0.34581310623995, 0.32074277511779, 0.28888254348354, 0.30744891244605, 0.25935573482131, 0.26361602609175, 0.26082786508776, 0.30635658615128, 0.23976535578339, 0.25873186907899, 0.26583872571828, 0.24596657582841, 0.24723758501531, 0.41017053235709, 0.39315911773099, 0.36968789682527, 0.29383464708276, 0.31154385830485, 0.30248548727292, 0.36181528942748, 0.27234521488117, 0.36586872012873, 0.30042908894006, 0.26472078989085, 0.28514533384609, 0.23050827676956, 0.28023452682183, 0.22382677203867, 0.23612014104421, 0.22242858194272, 0.2725477083491, 0.28114863576184, 0.22610810911747, 0.28333419616346, 0.2152034818852, 0.28262915338984, 0.25804706786919, 0.19776421106091, 0.27892209936259, 0.23950660259123, 0.20055740579992, 0.20805475369027, 0.19193460125913, 0.18330636388965, 0.21125458403176, 0.20974664482161, 0.25888098479216, 0.24134183531692, 0.18165186950525, 0.20998992666586, 0.21062957124083, 0.2349358601391, 0.19658172961775, 0.21908960238329, 0.10372950001727];
		
		
		//[0.014730675241416, 0.0078048792958529, 0.0021696510058821, 0.0033809571454736, 0.0036780896862776, 0.0] //removed as too low 
		
//	transitions = Array.fill(densitysource.size-2,{|j|  [densitysource[j],densitysource[j+1],densitysource[j+2]] }); 	
//	transitions= transitions.sort({ arg a, b; a[0] <= b[0] });
//	
	transitions = densitysource.collect{|array| Array.fill(array.size-2,{|j|  [array[j],array[j+1],array[j+2]] })}; 	
	transitions= transitions.flatten.sort({ arg a, b; a[0] <= b[0] });
	
	
	
		transitionnow = transitions.choose; 
		transitionpos = 3; 
	}

	next {
		var selection; 
		var nearest, score; 
		
		
		//(transitionnow.isNil) || 
		if(transitionpos==3) {
			
			score = 999.9; 
			nearest =  0; 
			//nearest
			
			selection = transitionnow[2]; 
			
			transitions.do{|val,j|  var now= val[0]; var test = abs(now-selection);  if(test<score) {score= test; nearest=j}; };
			
			transitionnow = if(0.7.coin,{transitions[nearest]},{transitions.choose}); //later could choose closest to current?  
			
			transitionpos = 0; 
			
		}; 
		
		selection = transitionnow[transitionpos]; 
		
		if(0.5.coin) { selection = selection * rrand(1.1.reciprocal,1.1); }; 
		
		transitionpos = transitionpos + 1; 
		
		^selection; 
	}
	

	createEnvelope {|length| 
		var array; 
		var num; 
		
		
		num = length.roundUp.asInteger + 1; 
		array = Array.fill(num, {this.next; }); 
		
		//line segments assumed
		^Env(array, 1.0.dup(num-1) ); 
		
	}
	
	
	
}