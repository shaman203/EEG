clear all
SampleDir = 'blink\';
EventMarker = 2; % code of the event marker to extract
PointCount = 600; %number of measurements arount the marker to extract
ChannelToDisplay = 13;
saveSaxFilename = 'blinksax.csv';
saveEventFilename = 'blinkevent.csv';
nseg          = 8;
alphabet_size = 5;
groupBy = 5;
saxcounter = 1;
eventcounter = 1;

pointsPerSeg = PointCount / nseg;

[markers,data] = EpocCVSDataLoader(SampleDir);
[x,y] = EventExtractor(data,EventMarker,PointCount,markers);
selected_x = [];
selected_y = [];
for i = 1:size(x,1)
    
    d = x(i,1+(ChannelToDisplay-1)*PointCount:ChannelToDisplay*PointCount) - 4000;
    figure(1);
    subplot(2,1,1);
    plot(d);
    subplot(2,1,2);
    plot(smooth(d,20));
    figure(2);
    str = sax_demo(smooth(d,20),nseg, alphabet_size);
    
    a = input('Accept this sample (y/n)? ','s')
    if strcmpi(a,'y')
       
        for j = 1:size(str,2)-groupBy+1
            disp(str(j:j+groupBy-1));
            rawsignal = d(1+(j-1)*pointsPerSeg:(j+groupBy-1)*pointsPerSeg);
            figure(3);
            plot(rawsignal);
            in = input('Is this an event seq?','s');
            if(in == 'y')
                saxexamples(saxcounter,:) = [str(j:j+groupBy-1) 1];
                
                selected_x = [selected_x; rawsignal];
                selected_y = [selected_y; y(i)];
                
            else
                saxexamples(saxcounter,:) = [str(j:j+groupBy-1) -1];
            end
            saxcounter = saxcounter + 1;
            close(intersect(findall(0,'type','figure'),3));
        end
    end
    close(intersect(findall(0,'type','figure'),1));
    close(intersect(findall(0,'type','figure'),2));
    
end
%csvwrite(saveFilename,[selected_x selected_y]);